package com.example.intellisoftgloriaaccesomovil.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.devkit.api.Misc;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.BD.Empleado;
import com.example.intellisoftgloriaaccesomovil.BD.EmpleadoBio;
import com.example.intellisoftgloriaaccesomovil.BD.Marca;
import com.example.intellisoftgloriaaccesomovil.R;
import com.example.intellisoftgloriaaccesomovil.TabItems.TabAcceso2;
import com.suprema.BioMiniFactory;
import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


public class IntellisoftGloriaAccesoMovil_ControlAcceso extends AppCompatActivity implements View.OnClickListener {

    Button buttonentrada;
    ConexionSQLiteHelper conn;

    private Context context;
    ArrayList<Marca> listaMarca;


    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent = null;
    private BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public static IBioMiniDevice mCurrentDevice = null;
    private IntellisoftGloriaAccesoMovil_ControlAcceso mainContext;
    public final static String TAG = "BioMini Sample";
    private EditText mLogView;
    private ScrollView mScrollLog = null;


    class UserData {
        String name;
        byte[] template;

        public UserData(String name, byte[] data, int len) {
            this.name = name;
            this.template = Arrays.copyOf(data, len);
        }
    }

    private ArrayList<UserData> mUsers = new ArrayList<>();

    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();

    synchronized public void printState(final CharSequence str) {
        log(str.toString());

    }

    synchronized public void log(final String msg) {
        Log.e(TAG, msg);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mLogView != null) {
                    //mLogView.getText().clear();
                    mLogView.append(msg + "\n");
                    if (mScrollLog != null) {
                        //mScrollLog.scrollTo(0, mScrollLog.getBottom());
                        mScrollLog.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                } else {
                    Log.e("", msg);
                }
            }
        });
    }

    synchronized public void printRev(final String msg) {
        log(msg);
    }


    /**
     * usb permission request
     */
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (device != null) {
                            if (mBioMiniFactory == null) return;
                            mBioMiniFactory.addDevice(device);
                        }
                    } else {
                        Log.e(TAG, "PERMISO DENEGADO POR EL SERVICIO" + device);
                    }
                }
            }
        }
    };

    // check the finger print module
    public void checkDevice() {
        if (mUsbManager == null) return;

        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviceIter = deviceList.values().iterator();
        while (deviceIter.hasNext()) {
            UsbDevice _device = deviceIter.next();
            if (_device.getVendorId() == 0x16d1) {
                //Suprema vendor ID

                mUsbManager.requestPermission(_device, mPermissionIntent);
                if (mBioMiniFactory == null) return;
                mBioMiniFactory.addDevice(_device);
                mCurrentDevice = mBioMiniFactory.getDevice(0);
            } else {
            }
        }

    }

    /**
     * power on/off the fingerprint scanner
     * If use M8 need power on when oncreate
     *
     * @param isOpen
     */
    private void switchPower(boolean isOpen) {
        Misc.nativeUsbMode(isOpen ? 1 : 0);
        Misc.fingerEnable(isOpen);

    }

    @Override
    public void onClick(View view) {

        Log.e(TAG, "init button click method....");
        switch (view.getId()) {
            case R.id.buttonCheckDevice:
                checkDevice();
                break;
            case R.id.buttonVerify:
                verify(view);
                break;
            case R.id.buttonSalida:
                salida(view);
                break;
            case R.id.buttonEntrada:
                entrada(view);
                break;
        }

    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        switchPower(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_mini);

        conn = new ConexionSQLiteHelper(this);

        verifyStoragePermissions(this);
        mScrollLog = (ScrollView) findViewById(R.id.scrollLog);
        mLogView = (EditText) findViewById(R.id.editLog);
        mainContext = this;
        mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.SHIGH;

        findViewById(R.id.buttonCheckDevice).setOnClickListener(this);
        findViewById(R.id.buttonVerify).setOnClickListener(this);
        findViewById(R.id.buttonSalida).setOnClickListener(this);
        findViewById(R.id.buttonEntrada).setOnClickListener(this);

        if (mBioMiniFactory != null) {
            mBioMiniFactory.close();
        }

        if (!mbUsbExternalUSBManager) {
            Button btn_checkDevice = (Button) findViewById(R.id.buttonCheckDevice);
            // btn_checkDevice.setClickable(false);
            //btn_checkDevice.setEnabled(false);
            //checkDevice();
            ((Button) findViewById(R.id.buttonCheckDevice)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkDevice();
                }
            });
        } else {
            ((Button) findViewById(R.id.buttonCheckDevice)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkDevice();
                }
            });
        }

        if (mbUsbExternalUSBManager) {
            mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
            mBioMiniFactory = new BioMiniFactory(mainContext, mUsbManager) {
                @Override
                public void onDeviceChange(DeviceChangeEvent event, Object dev) {

                    if (event == DeviceChangeEvent.DEVICE_ATTACHED && mCurrentDevice == null) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int cnt = 0;
                                while (mBioMiniFactory == null && cnt < 20) {
                                    SystemClock.sleep(1000);
                                    cnt++;
                                }
                                if (mBioMiniFactory != null) {
                                    mCurrentDevice = mBioMiniFactory.getDevice(0);
                                    if (mCurrentDevice != null) {
                                    }
                                }
                            }
                        }).start();
                    } else if (mCurrentDevice != null && event == DeviceChangeEvent.DEVICE_DETACHED && mCurrentDevice.isEqual(dev)) {
                        mCurrentDevice = null;
                    }
                }
            };
            //
            mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            registerReceiver(mUsbReceiver, filter);
            checkDevice();


        } else {
            mBioMiniFactory = new BioMiniFactory(mainContext) {
                @Override
                public void onDeviceChange(DeviceChangeEvent event, Object dev) {

                    if (event == DeviceChangeEvent.DEVICE_ATTACHED && mCurrentDevice == null) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int cnt = 0;
                                while (mBioMiniFactory == null && cnt < 20) {
                                    SystemClock.sleep(1000);
                                    cnt++;
                                }
                                if (mBioMiniFactory != null) {
                                    mCurrentDevice = mBioMiniFactory.getDevice(0);
                                    if (mCurrentDevice != null) {
                                    }
                                }
                            }
                        }).start();
                    } else if (mCurrentDevice != null && event == DeviceChangeEvent.DEVICE_DETACHED && mCurrentDevice.isEqual(dev)) {
                        mCurrentDevice = null;
                    }
                }
            };
        }
        mBioMiniFactory.setTransferMode(IBioMiniDevice.TransferMode.MODE2);
        printRev("" + mBioMiniFactory.getSDKInfo());



        try {
            File ff = new File(Environment.getExternalStorageDirectory()+"/Finger/");
            for(File fl:ff.listFiles()){
                Log.e(TAG,"file path:"+fl.getAbsolutePath()+" "+fl.getName());
                byte[] buf = toByteArray2(fl.getAbsolutePath());
                mUsers.add(new UserData(fl.getName(),buf,buf.length));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


/*
        //TODO   Test data,  get the templates from database and put them to the List;
        File file = new File(Environment.getExternalStorageDirectory()+"/Finger/cc.tmp");
        Log.e(TAG,"path:"+Environment.getExternalStorageDirectory()+"/Finger/cc.tmp"+file.exists());

        try {
            byte[] buffer = toByteArray2(file.getAbsolutePath());
            for(int i=0;i<2000;i++){
                mUsers.add(new UserData(file.getName()+i,buffer,buffer.length));
            }
            Log.e(TAG,"list size:"+mUsers.size());

            File ff = new File(Environment.getExternalStorageDirectory()+"/Finger/");


            for(File fl:ff.listFiles()){
                Log.e(TAG,"file path:"+fl.getAbsolutePath()+" "+fl.getName());
                byte[] buf = toByteArray2(fl.getAbsolutePath());
                mUsers.add(new UserData(fl.getName(),buf,buf.length));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
*/


        try {
            File ff = new File(Environment.getExternalStorageDirectory()+"/Finger/");
            for(File fl:ff.listFiles()){
                Log.e(TAG,"file path:"+fl.getAbsolutePath()+" "+fl.getName());
                byte[] buf = toByteArray2(fl.getAbsolutePath());
                mUsers.add(new UserData(fl.getName(),buf,buf.length));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //android 6.0+ need  dynamic get storage read/write permission
    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        switchPower(false); //power off the scanner
        if (mBioMiniFactory != null) {
            mBioMiniFactory.close();
            mBioMiniFactory = null;
        }
        super.onDestroy();
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            log("permission granted");
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        requestPermission();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        switchPower(false);
        if (mBioMiniFactory != null) {
            mBioMiniFactory.close();
            mBioMiniFactory = null;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //convert a file to  byte[]
    public static byte[] toByteArray2(String filename) throws IOException {

        File f = new File(filename);
        /*if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }*/
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    //identify the Fingerprint you captured from the Fingerprint list
    public void verify(View view) {
        if (mCurrentDevice != null) {
            // capture fingerprint image
            IBioMiniDevice.CaptureOption option = new IBioMiniDevice.CaptureOption();
            mBioMiniFactory.setTransferMode(IBioMiniDevice.TransferMode.MODE2);
            mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.TIMEOUT, 15000));
            mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.TEMPLATE_TYPE, IBioMiniDevice.TemplateType.SUPREMA.value() ));
            option.frameRate = IBioMiniDevice.FrameRate.MID;
            option.captureTemplate = true;
            // capture fingerprint image
            mCurrentDevice.captureSingle(option,
                    new CaptureResponder() {
                        @Override
                        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                                   final IBioMiniDevice.TemplateData capturedTemplate,
                                                   final IBioMiniDevice.FingerState fingerState) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (capturedImage != null) {
                                        ImageView iv = (ImageView) findViewById(R.id.imagePreview);
                                        if (iv != null) {
                                            iv.setImageBitmap(capturedImage);
                                        }
                                    }
                                }
                            });
                            if (capturedTemplate != null) {
                                boolean isMatched = false;
                                String matchedName = "";
                                long a=System.currentTimeMillis();
                                for (UserData ud : mUsers) {
                                    if (mCurrentDevice.verify(capturedTemplate.data, capturedTemplate.data.length, ud.template, ud.template.length)) {
                                        isMatched = true;
                                        matchedName = ud.name;
                                        //break;
                                    }
                                    Log.e(TAG,"USUARIO:"+ud.name+" :"+isMatched);
                                }
                                long b=System.currentTimeMillis()-a;
                                log("");
                                log("------------------------------------");
                                log("Tiempo de busqueda:"+b);
                                Log.e(TAG," Tiempo usado"+b);
                                if (isMatched) {

                                    //IntellisoftGloriaAccesoMovil_ConsultaAccesos.listaEmpleado.add(listaEmpleado);

                                    log("Marca encontrada : " + matchedName);
                                    printState(getResources().getText(R.string.verify_ok));
                                    log("------------------------------------");
                                    log("");
                                } else {
                                    log("Marca no encontrada : ");
                                    printState(getResources().getText(R.string.verify_not_match));
                                    log("------------------------------------");
                                    log("");
                                }
                            } else {
                                log("<<ERROR>> Template no extraído...");
                                printState(getResources().getText(R.string.verify_fail));
                                log("------------------------------------");
                                log("");
                            }
                            return true;
                        }

                        @Override
                        public void onCaptureError(Object context, int errorCode, String error) {
                            log("ERROR DE CAPTURA : " + error);
                            printState(getResources().getText(R.string.capture_fail));
                        }
                    }, true);
        }
    }

    private void entrada (View view) {



            Intent intent = new Intent(IntellisoftGloriaAccesoMovil_ControlAcceso.this, TabAcceso2.class);
        //    intent.putExtra("marca",mUsers.get(intent.getDataString()));
         //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


/*
        Intent intent = new Intent(BioMiniActivity.this, TabAcceso2.class);
        startActivity(intent);
*/

    }


    private void salida(View view) {

        Intent i = new Intent(IntellisoftGloriaAccesoMovil_ControlAcceso.this, IntellisoftGloriaAccesoMovil_Options.class);
        startActivity(i);

    }
}


package com.soarsky.soarskylib;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Andriod_Car_App
 * 作者： Elvis
 * 时间： 2017/5/17
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class BluetoothSoarskyService {

    /**
     * 蓝牙
     */
    public interface OnBluetoothEvent {
        /***
         * @param bytes
         * @param length
         */
        void onDataReceived(byte[] bytes, int length);

        /**
         *
         */
        void onConnectFail();

        void onConnectSuccess();

        void onSenderSuccess();

        void onSenderFail();

        void onStateChange(int oldState, int newState);

        void onConnectionLost();
    }

    public static class DefaultOnBluetoothEvent implements OnBluetoothEvent{

        /***
         * @param bytes
         * @param length
         */
        @Override
        public void onDataReceived(byte[] bytes, int length) {

        }

        /**
         *
         */
        @Override
        public void onConnectFail() {

        }

        @Override
        public void onConnectSuccess() {

        }

        @Override
        public void onSenderSuccess() {

        }

        @Override
        public void onSenderFail() {

        }

        @Override
        public void onStateChange(int oldState, int newState) {

        }

        @Override
        public void onConnectionLost() {

        }
    };

    private List<OnBluetoothEvent> onBluetoothEventList = new ArrayList<>();

    public void setOnBluetoothEvent(OnBluetoothEvent onBluetoothEvent) {
        this.onBluetoothEventList.add(onBluetoothEvent);
    }

    // Debugging
    private static final String TAG = "BluetoothSoarskyService";
    private static final UUID SOARSKY_UUID_INSECURE =
            UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private final BluetoothAdapter mAdapter;
    private final Handler mHandler;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private int mState;

    /**
     * 闲置状态
     */
    public static final int STATE_NONE = 0;
    /**
     * 正在连接状态
     */
    public static final int STATE_CONNECTING = 1;
    /**
     * 已连接状态
     */
    public static final int STATE_CONNECTED = 2;

    /**
     * BluetoothSoarskyService 服务。提供连接发送API
     */
    public BluetoothSoarskyService() {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = STATE_NONE;
        HandlerThread handlerThread = new HandlerThread("BluetoothSoarskyService");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
    }

    public Handler getHandler() {
        return mHandler;
    }

    /**
     * Return the current connection state.
     */
    public synchronized int getState() {
        return mState;
    }

    /**
     * 初始化
     */
    public synchronized void init() {
        Log.d(TAG, "start");
        // Cancel any thread attempting to make a connection
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
    }

    /**
     * 连接指定的蓝牙设备
     *
     * @param device 连接指定的蓝牙设备
     */
    public synchronized void connect(BluetoothDevice device) {
        Log.d(TAG, "connect to: " + device);

        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(device);
        mConnectThread.start();
    }


    public synchronized void disConnect() {
        stop();
    }

    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     *
     * @param socket The BluetoothSocket on which the connection was made
     */
    public synchronized void connected(BluetoothSocket socket) {
        // Cancel the thread that completed the connection
        if (mConnectThread != null) {
            mConnectThread = null;
        }
        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();
    }

    /**
     * Stop all threads
     */
    private synchronized void stop() {
        Log.d(TAG, "stop");

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                for (OnBluetoothEvent onBluetoothEvent : onBluetoothEventList) {
                    onBluetoothEvent.onStateChange(mState, STATE_NONE);
                }
            }
        });
        mState = STATE_NONE;
    }

    /**
     * 发送数据
     *
     * @param out 需要发送的数据
     * @see ConnectedThread#write(byte[])
     */
    public void write(byte[] out) {
        // Create temporary object
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (mState != STATE_CONNECTED) return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    /**
     * 连接失败
     */
    private void connectionFailed() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mState != STATE_NONE) {
                    for (OnBluetoothEvent onBluetoothEvent : onBluetoothEventList) {
                        onBluetoothEvent.onStateChange(mState, STATE_NONE);
                    }
                }
                for (OnBluetoothEvent onBluetoothEvent : onBluetoothEventList) {
                    onBluetoothEvent.onConnectFail();
                }
            }
        });
        mState = STATE_NONE;
    }

    /**
     * 连接丢失
     */
    private void connectionLost() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mState != STATE_NONE) {
                    for (OnBluetoothEvent onBluetoothEvent : onBluetoothEventList) {
                        onBluetoothEvent.onStateChange(mState, STATE_NONE);
                    }
                }
                for (OnBluetoothEvent onBluetoothEvent : onBluetoothEventList) {
                    onBluetoothEvent.onConnectionLost();
                }
            }
        });

        mState = STATE_NONE;
    }


    /**
     * 连接线程，给定指定蓝牙设备
     */
    private class ConnectThread extends Thread {
        private BluetoothSocket mmSocket;
        private BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            mmDevice = device;
            BluetoothSocket tmp = null;

            try {
                tmp = device.createInsecureRfcommSocketToServiceRecord(
                        SOARSKY_UUID_INSECURE);
            } catch (Exception e) {
                Log.e(TAG, "Socket Type: create() failed", e);
                // 这里不用通知连接失败，在连接的时候，会进行再判断，再次通知调用者连接失败
                return;
            }
            mmSocket = tmp;

            if (mState != STATE_CONNECTING) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (OnBluetoothEvent onBluetoothEvent : onBluetoothEventList) {
                            onBluetoothEvent.onStateChange(mState, STATE_CONNECTING);
                        }
                    }
                });
            }
            mState = STATE_CONNECTING;
        }

        public void run() {
            setName("ConnectThread");
            // Always cancel discovery because it will slow down a connection
            mAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                if (mmSocket != null) {
                    mmSocket.connect();
                } else {
                    throw new Exception("socket create fail");
                }
            } catch (Exception e) {
                // Close the socket
                try {
                    if (mmSocket != null) {
                        mmSocket.close();
                    }
                } catch (IOException e2) {
                    Log.e(TAG, "unable to close() socket during connection failure", e2);
                }
                connectionFailed();
                return;
            }
            // Start the connected thread
            connected(mmSocket);
        }

        public void cancel() {
            try {
                if (mmSocket != null) {
                    mmSocket.close();
                }
                mmSocket = null;
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    /**
     * 连接线程
     */
    private class ConnectedThread extends Thread {
        private BluetoothSocket mmSocket;
        private InputStream mmInStream;
        private OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
            if (mState != STATE_CONNECTED) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (OnBluetoothEvent onBluetoothEvent : onBluetoothEventList) {
                            onBluetoothEvent.onStateChange(mState, STATE_CONNECTED);
                            onBluetoothEvent.onConnectSuccess();
                        }
                    }
                });

            }
            mState = STATE_CONNECTED;
        }

        public void run() {
            Log.i(TAG, "BEGIN mConnectedThread");
            byte[] buffer = new byte[1024];
            while (mState == STATE_CONNECTED) {
                try {
                    // Read from the InputStream
                    final int bytes = mmInStream.read(buffer);
                    final byte[] outBuffer = new byte[bytes];
                    System.arraycopy(buffer, 0, outBuffer, 0, bytes);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (OnBluetoothEvent onBluetoothEvent : onBluetoothEventList) {
                                onBluetoothEvent.onDataReceived(outBuffer, bytes);
                            }
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                    connectionLost();
                    break;
                }
            }
        }

        /**
         * 发送数据
         *
         * @param buffer 发送的数据
         */
        public void write(byte[] buffer) {
            try {
                if (mmOutStream != null) {
                    mmOutStream.write(buffer);
                } else {
                    throw new Exception("OutStream is null");
                }
            } catch (Exception e) {
                Log.e(TAG, "Exception during write", e);
                connectionLost();
            }
        }

        public void cancel() {
            try {
                if (mmSocket != null) {
                    mmSocket.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
}

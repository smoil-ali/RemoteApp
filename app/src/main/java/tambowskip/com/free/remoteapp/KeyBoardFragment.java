package tambowskip.com.free.remoteapp;

import android.content.Context;
import android.graphics.MaskFilter;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import io.evercam.network.Main;


public class KeyBoardFragment extends Fragment implements View.OnTouchListener ,View.OnClickListener, TextWatcher {

    private EditText typeHereEditText;
    private Button ctrlButton, altButton, shiftButton, enterButton, tabButton, escButton, printScrButton, backspaceButton;
    private Button deleteButton, clearTextButton;
    private Button nButton, tButton, wButton, rButton, fButton, zButton;
    private Button cButton, xButton, vButton, aButton, oButton, sButton,bButton,dButton,eButton,gButton,hButton,iButton, jButton,kButton,lButton,mButton,qButton,uButton,yButton;
    private Button ctrlAltTButton, ctrlShiftZButton, altF4Button;
    private String previousText = "";


    public KeyBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_key_board, container, false);

        initialization(view);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.Keyboard));
    }


    private void initialization(View rootView) {
        typeHereEditText = (EditText) rootView.findViewById(R.id.typeHereEditText);
        ctrlButton = (Button) rootView.findViewById(R.id.ctrlButton);
        altButton = (Button) rootView.findViewById(R.id.altButton);
        shiftButton = (Button) rootView.findViewById(R.id.shiftButton);
        enterButton = (Button) rootView.findViewById(R.id.enterButton);
        tabButton = (Button) rootView.findViewById(R.id.tabButton);
        escButton = (Button) rootView.findViewById(R.id.escButton);
        printScrButton = (Button) rootView.findViewById(R.id.printScrButton);
        backspaceButton = (Button) rootView.findViewById(R.id.backspaceButton);
        deleteButton = (Button) rootView.findViewById(R.id.deleteButton);
        clearTextButton = (Button) rootView.findViewById(R.id.clearTextButton);
        nButton = (Button) rootView.findViewById(R.id.nButton);
        tButton = (Button) rootView.findViewById(R.id.tButton);
        wButton = (Button) rootView.findViewById(R.id.wButton);
        rButton = (Button) rootView.findViewById(R.id.rButton);
        fButton = (Button) rootView.findViewById(R.id.fButton);
        zButton = (Button) rootView.findViewById(R.id.zButton);
        cButton = (Button) rootView.findViewById(R.id.cButton);
        xButton = (Button) rootView.findViewById(R.id.xButton);
        vButton = (Button) rootView.findViewById(R.id.vButton);
        aButton = (Button) rootView.findViewById(R.id.aButton);
        oButton = (Button) rootView.findViewById(R.id.oButton);
        sButton = (Button) rootView.findViewById(R.id.sButton);
        bButton=rootView.findViewById(R.id.bButton);
        dButton=rootView.findViewById(R.id.dButton);
        eButton=rootView.findViewById(R.id.eButton);
        gButton=rootView.findViewById(R.id.gButton);
        hButton=rootView.findViewById(R.id.hButton);
        iButton=rootView.findViewById(R.id.iButton);
        jButton=rootView.findViewById(R.id.jButton);
        kButton=rootView.findViewById(R.id.kButton);
        lButton=rootView.findViewById(R.id.lButton);
        mButton=rootView.findViewById(R.id.mButton);
        qButton=rootView.findViewById(R.id.qButton);
        uButton=rootView.findViewById(R.id.uButton);
        yButton=rootView.findViewById(R.id.yButton);
        ctrlAltTButton = (Button) rootView.findViewById(R.id.ctrlAltTButton);
        ctrlShiftZButton = (Button) rootView.findViewById(R.id.ctrlShiftZButton);
        altF4Button = (Button) rootView.findViewById(R.id.altF4Button);
        ctrlButton.setOnTouchListener(this);
        altButton.setOnTouchListener(this);
        shiftButton.setOnTouchListener(this);
        backspaceButton.setOnClickListener(this);
        enterButton.setOnClickListener(this);
        tabButton.setOnClickListener(this);
        escButton.setOnClickListener(this);
        printScrButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        clearTextButton.setOnClickListener(this);
        nButton.setOnClickListener(this);
        tButton.setOnClickListener(this);
        wButton.setOnClickListener(this);
        rButton.setOnClickListener(this);
        fButton.setOnClickListener(this);
        zButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
        xButton.setOnClickListener(this);
        vButton.setOnClickListener(this);
        aButton.setOnClickListener(this);
        oButton.setOnClickListener(this);
        sButton.setOnClickListener(this);
        bButton.setOnClickListener(this);
        dButton.setOnClickListener(this);
        eButton.setOnClickListener(this);
        gButton.setOnClickListener(this);
        hButton.setOnClickListener(this);
        iButton.setOnClickListener(this);
        jButton.setOnClickListener(this);
        kButton.setOnClickListener(this);
        lButton.setOnClickListener(this);
        mButton.setOnClickListener(this);
        qButton.setOnClickListener(this);
        uButton.setOnClickListener(this);
        yButton.setOnClickListener(this);
        ctrlAltTButton.setOnClickListener(this);
        ctrlShiftZButton.setOnClickListener(this);
        altF4Button.setOnClickListener(this);
        typeHereEditText.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        final char ch = newCharacter(charSequence, previousText);
        if (ch == 0) {
            return;
        }
        AppExecutors.getInstance().getNetWorkCall().execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.printWriter.println("TYPE_CHARACTER");
                MainActivity.printWriter.flush();
                MainActivity.printWriter.println(Character.toString(ch));
                MainActivity.printWriter.flush();
            }
        });
        previousText = charSequence.toString();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private char newCharacter(CharSequence currentText, CharSequence previousText) {
        char ch = 0;
        int currentTextLength = currentText.length();
        int previousTextLength = previousText.length();
        int difference = currentTextLength - previousTextLength;
        if (currentTextLength > previousTextLength) {
            if (1 == difference) {
                ch = currentText.charAt(previousTextLength);
            }
        } else if (currentTextLength < previousTextLength) {
            if (-1 == difference) {
                ch = '\b';
            } else {
                ch = ' ';
            }
        }
        return ch;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.clearTextButton) {
            typeHereEditText.setText("");
        } else if (id == R.id.ctrlAltTButton || id == R.id.ctrlShiftZButton || id == R.id.altF4Button) {
            String message = "CTRL_SHIFT_Z";
            switch (id) {
                case R.id.ctrlAltTButton:
                    message = "CTRL_ALT_T";
                    break;
                case R.id.ctrlShiftZButton:
                    message = "CTRL_SHIFT_Z";
                    break;
                case R.id.altF4Button:
                    message = "ALT_F4";
                    break;
            }
            final String finalMessage = message;
            AppExecutors.getInstance().getNetWorkCall().execute(new Runnable() {
                @Override
                public void run() {
                    MainActivity.printWriter.println(finalMessage);
                    MainActivity.printWriter.flush();
                }
            });
        } else {
            int keyCode = 17;//dummy initialization
            String action = "TYPE_KEY";
            switch (id) {
                case R.id.enterButton:
                    keyCode = 10;
                    break;
                case R.id.tabButton:
                    keyCode = 9;
                    break;
                case R.id.escButton:
                    keyCode = 27;
                    break;
                case R.id.printScrButton:
                    keyCode = 154;
                    break;
                case R.id.deleteButton:
                    keyCode = 127;
                    break;
                case R.id.nButton:
                    keyCode = 78;
                    break;
                case R.id.tButton:
                    keyCode = 84;
                    break;
                case R.id.wButton:
                    keyCode = 87;
                    break;
                case R.id.rButton:
                    keyCode = 82;
                    break;
                case R.id.fButton:
                    keyCode = 70;
                    break;
                case R.id.zButton:
                    keyCode = 90;
                    break;
                case R.id.cButton:
                    keyCode = 67;
                    break;
                case R.id.xButton:
                    keyCode = 88;
                    break;
                case R.id.vButton:
                    keyCode = 86;
                    break;
                case R.id.aButton:
                    keyCode = 65;
                    break;
                case R.id.oButton:
                    keyCode = 79;
                    break;
                case R.id.sButton:
                    keyCode = 83;
                    break;
                case R.id.backspaceButton:
                    keyCode = 8;
                    break;
                case R.id.bButton:
                    keyCode=66;
                    break;
                case R.id.dButton:
                    keyCode=68;
                    break;
                case R.id.eButton:
                    keyCode=69;
                    break;
                case R.id.gButton:
                    keyCode=71;
                    break;
                case R.id.hButton:
                    keyCode=72;
                    break;
                case R.id.iButton:
                    keyCode=73;
                    break;
                case R.id.jButton:
                    keyCode=74;
                    break;
                case R.id.kButton:
                    keyCode=75;
                    break;
                case R.id.lButton:
                    keyCode=76;
                    break;
                case R.id.mButton:
                    keyCode=77;
                    break;
                case R.id.pButton:
                    keyCode=80;
                    break;
                case R.id.qButton:
                    keyCode=81;
                    break;
                case R.id.uButton:
                    keyCode=85;
                    break;
                case R.id.yButton:
                    keyCode=89;
                    break;

            }
            sendKeyCodeToServer(action, keyCode);
        }

    }

    private void sendKeyCodeToServer(final String action, final int keyCode) {
        AppExecutors.getInstance().getNetWorkCall().execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.printWriter.println(action);
                MainActivity.printWriter.flush();
                MainActivity.printWriter.println(keyCode);
                MainActivity.printWriter.flush();
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String action = "KEY_PRESS";
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            action = "KEY_PRESS";
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            action = "KEY_RELEASE";
        }
        int keyCode = 17;//dummy initialization
        switch (v.getId()) {
            case R.id.ctrlButton:
                keyCode = 17;
                break;
            case R.id.altButton:
                keyCode = 18;
                break;
            case R.id.shiftButton:
                keyCode = 16;
                break;
        }
        sendKeyCodeToServer(action, keyCode);
        return false;
    }
}

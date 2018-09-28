# StyleLibApp


## SwipeBackActivity

In your main Layout do:
```Xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/layout"
    tools:context=".MainActivity">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/oldScreen" />

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/newScreen" />

</RelativeLayout>
```

It must be `RelativeLayout` or `StyleBottomLineLayout`

In your main Activity do:
```Java
public class MainActivity extends SlideBackActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOldScreen(R.layout.activity_main2, R.id.oldScreen);
        setNewScreen(R.layout.activity_main3, R.id.newScreen);

        findViewById(R.id.oldScreen).setOnClickListener(v -> openNewScreen());
    }
}
```

## BottomLineLayout

You can use this in `SwipeBackActivity` instead of the `RelativeLayout`

```Xml
<com.dertyp7214.stylelib.layouts.StyleBottomLineLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```
# backgraoundView
### 避免编写很多背景 或者按下状态XML 文件   直接设置常用控件的背景 和选中样式。

How to
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle

Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


Step 2. Add the dependency

```
	dependencies {
	        implementation 'com.github.pengshaokui:backgroundView:v1.0.0'
	}

```

## GIF

![img](https://github.com/pengshaokui/backgroundView/blob/main/gif/Screenrecorder20211106x.gif)
![img](https://user-images.githubusercontent.com/24237865/63502889-6916f300-c509-11e9-893a-d634f1c6a850.gif)
<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/63502889-6916f300-c509-11e9-893a-d634f1c6a850.gif" width="32%"/>
</p>
## 使用方法

以Textview为例  暂时支持的有

-  DecorationTextview
-  DecorationButton
-  DecorationEditText
-  DecorationImageview
-  DecorationLinearlayout
-  DecorationRelativeLayout

```
<com.psk.view.backgroundview.DecorationTextview
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_margin="20dp"
            android:gravity="center"
            android:textSize="20dp"
            android:text="DecorationTextview"
            app:decoration_conner_radius="10dp"//  圆角大小
            app:decoration_solid_color="#5169E3"// 填充颜色
            app:decoration_solid_select_color="#ffff00"// pressed，checked，selected，!enabled颜色 
            app:decoration_stroke_color="@color/black"// 描边颜色
            app:decoration_stroke_select_color="#ff0000"//描边选中后颜色
            app:decoration_stroke_width="3dp"// 描边宽度
            //建议点击有颜色改变时才使用文字颜色配置，文字颜色不变的使用setcolor即可
            app:decoration_pressed_text_color="#818183"//  文字按下后颜色
            app:decoration_normal_text_color="@color/white"// 文字正常时候颜色
            //带渐变的背景
            app:decoration_gradient_angle="180" //角度
            app:decoration_gradient_end_color="#ffff00"//结束位置颜色
            app:decoration_gradient_start_color="#ff00ff"//开始位置颜色
            ></com.psk.view.backgroundview.DecorationTextview>
```

### DecorationImageview


```
<com.psk.view.backgroundview.DecorationImageview
            android:layout_width="350dp"
            android:layout_height="250dp"
            android:background="@drawable/ic_launcher_background"
            android:clickable="true"
            app:decoration_image_normal="@drawable/ic_launcher_foreground"//默认图片
            app:decoration_image_pressed="@drawable/ic_launcher_foreground2"//点击图片
            ></com.psk.view.backgroundview.DecorationImageview>
```

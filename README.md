#  ROS 项目



### 打包相关

```shell

//打包正式环境
gradlew assembleRelease

//打包测试环境
gradlew assembleDebug


关闭应用
adb shell am force-stop cn.cameltec.kissabcpad
重启
adb  reboot

adb connect  192.168.20.41:5559

开始抓logcat日志  ctrl+C 停止抓取
adb  logcat > log.txt
adb  logcat  BaseSingEngine:I -s

获取设备dp
adb shell getprop ro.sf.lcd_density
创建文件夹以及文件
mkdir temp
touch log.txt

查看到系统的启动app信息  可以用来隐式或者显式打开app
adb  shell
logcat | grep START

//卸载应用
adb uninstall cn.cameltec.kissabcpad
adb uninstall com.rocky.colorfulshells
adb uninstall com.rocky.mathematics
adb shell pm clear  cn.cameltec.kissabcpad

gradlew :app:dependencies

如三个包都打  AS-gradle-app-build-assemblePad更快一些

连接夜神模拟器
adb  connect  127.0.0.1:62001
连接海马玩模拟器
adb connect 127.0.0.1:53001
连接木木模拟器
adb  connect  127.0.0.1:7555

获取本地记录日志到桌面
adb pull /sdcard/Android/data/cn.cameltec.kissabcpad/files/log/log%date:~0,4%%date:~5,2%%date:~8,2%.txt  C:\Users\87969\Desktop\
adb pull /data/data/com.win.pic.test/cache/image_manager_disk_cache/45d6b7c1457e19452ac083fc1455940ad2087d7a2319f094db6fb17e182ea25c.0  C:\Users\87969\Desktop\


adb pull /sdcard/Android/data/cn.cameltec.kissabcpad/files/bjyLogFile/20200217.log  C:\Users\87969\Desktop\
adb pull /data/user/0/com.tal.abctime  C:\Users\87969\Desktop\
adb pull /storage/emulated/0/Android/data/cn.cameltec.kissabcpad/files/test/  C:\Users\87969\Desktop\切片\
adb pull /storage/emulated/0/Android/data/cn.cameltec.kissabcpad/files/record/e8d9597a01f811ebb069f35fc67da6b7.wav  C:\Users\87969\Desktop\
清单文件异常
gradlew processPadDebugManifest --stacktrace

//打开开发助手
adb shell am start -n   cn.trinea.android.developertools/c.b.a
adb shell am start -a android.media.action.STILL_IMAGE_CAMERA  启动camera
//打开设置
adb shell am start com.android.settings/com.android.settings.Settings
//打开录音机
adb shell am  start  com.android.soundrecorder/.SoundRecorder
//浏览器
adb shell  am start   com.android.browser/com.android.browser.BrowserActivity

//本地储存卡补丁路径 【需要把jar放进去】
adb push  sophix-patch.jar  /storage/emulated/0/sophix-patch.jar
adb shell input text  "cn.cameltec.kissabcpad"
adb shell am force-stop cn.cameltec.kissabcpad
adb shell input text  "/storage/emulated/0/sophix-patch.jar"
adb shell  am start   com.taobao.sophix.debugtool/com.taobao.android.debugtool.activity.MainActivity
adb shell input text "https://rocky123.at.baijiayun.com/web/playback/index?classid=20082752718313
adb shell input text "&amp;"
adb shell input text "ZHHTYDY192014"

adb shell input text "on_-iIPf7y5Abrfz3AFHfkscqS9QYiFhuCVE7kBdyq_S7rd3gjKKmuu-wzU3cDPcjAk6Qj2pOAo"

adb shell  am start   cn.cameltec.kissabcpad.debug/cn.cameltec.kissabc.pad.LaunchActivity

//百家云课堂
adb shell  am start   com.baijia.live/com.baijia.live.avtivity.SplashActivity
adb shell  am start   com.baijiayun.videoplayerui.demo/.LauncherActivity

//打开pad启动页
adb shell  am start   cn.cameltec.kissabctrial/cn.cameltec.kissabc.pad.LaunchActivity
adb shell  am start   com.rocky.colorfulshells/.ui.main.MainActivity



查看md5 和 sha1  输入密码就可以获取了
keytool -list -v -keystore keystore/release_keystore.keystore

测试

 C0CDF60B1A0321164A5A6337A2D486B783C26847

```






AndroidLocation
===================================
  获取经纬度，获取原则：异步通过获取GPS,网络获取经纬度

  
科普
-----------------------------------
手机定位：
* `GPS`模块：`GPS`方式准确度是最高的，但是它的缺点也非常明显：
    1，比较耗电；</br>
    2，绝大部分用户默认不开启`GPS`模块；</br>
    3，从`GPS`模块启动到获取第一次定位数据，可能需要比较长的时间；</br>
    4，室内几乎无法使用。这其中，缺点2,3都是比较致命的。需要指出的是，GPS走的是卫星通信的通道，在没有网络连接的情况下也能用。</br>
* 基站定位 大致思路就是采集到手机上的基站ID号（`cellid`）和其它的一些信息（`MNC`，`MCC`，`LAC`等等），</br>
然后通过网络访问一些定位服务，获取并返回对应的经纬度坐标。基站定位的精确度不如`GPS`，但好处是能够在室内用，只要网络通畅就行。</br>
* `WIFI`定位 和基站定位类似，这种方式是通过获取当前所用的wifi的一些信息，然后访问网络上的定位服务以获得经纬度坐标。</br>
因为它和基站定位其实都需要使用网络，所以在Android也统称为`Network`方式。

所以此项目包含的即`GPS`定位和网络定位，基站定位也无非是网络定位的一种

-----------------------------------
  
### 权限

此项目中囊括了针对`androidM`系统访问权限的集成<br/>
[感谢PermissionGen](https://github.com/hongyangAndroid/PermissionGen)<br/>

### 使用
####定位
初始化
    
```java
locationAPI = new LocationAPI(getApplicationContext());
locationAPI.getChangeCurLocation(new HalopayLocationListener() {

    @Override
    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String paramString) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String paramString) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLocationFail(String paramString) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLocationChanged(HalopayLocation paramYongcheLocation) {
        // TODO Auto-generated method stub
        Log.i("纬度：", "" + paramYongcheLocation.getLatitude());//纬度
        Log.i("经度：", "" + paramYongcheLocation.getLongitude());//经度
    }
});
```

获取经纬度：

```java
tv_location_base.setText("纬度:" + LocationAPI.getLastKnownLocation().getLatitude() + ",经度：" + LocationAPI.getLastKnownLocation().getLongitude());
```

#### 权限
* 申请权限
```java
MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
```
* 处理权限回调
```java
@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
}
```
* 是否弹出解释
```java
if (!MPermissions.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUECT_CODE_SDCARD)){
    MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
}
```
如果需要解释，会自动执行使用`@ShowRequestPermissionRationale`注解的方法。

授权成功以及失败调用的分支方法通过注解`@PermissionGrant`和`@PermissionDenied`进行标识，详细参考我项目中app例子。

###注意
本文中利用了`AndroidAnnotations`注解框架
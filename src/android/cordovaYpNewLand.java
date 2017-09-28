package cordova.plugin.ypNewPland;
import android.content.Intent;
import android.os.Build;
import com.newland.mesdk.demo.scan.view.ScanViewActivity;
import com.yp.newLand.utils.PrintUtil;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
public class cordovaYpNewLand extends CordovaPlugin{
    private CallbackContext context;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.context=callbackContext;
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        if (action.equals("print")) {
            String message = args.getString(0);
            this.print(message, callbackContext);
            return true;
        }
        if (action.equals("scan")) {
            String message = args.getString(0);
            this.scan(message, callbackContext);
            return true;
        }
        if (action.equals("getSer")) {
            String message = args.getString(0);
            this.getSer(callbackContext);
            return true;
        }
        return false;
    }
    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(Build.SERIAL);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    /**
     * 获取机器唯一标识
     * @param callbackContext
     */
    private void getSer(CallbackContext callbackContext) {
        try
        {
            callbackContext.success(Build.SERIAL);
        }
        catch(Exception ex){
            callbackContext.error("机器编码获取失败,请检查设备权限");
        }
    }
    /**
     * 打印插件
     * @param message
     * @param callbackContext
     */
    private void print(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            PrintUtil.getInstance().init(message);
            callbackContext.success(1);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    /**
     * 扫描插件
     * @param message
     * @param callbackContext
     */
    private void scan(String message, CallbackContext callbackContext) {
        String model= android.os.Build.MANUFACTURER;
        if("newland".equals(model.toLowerCase())){
            if (message != null && message.length() > 0) {
                Intent intent=new Intent(this.cordova.getActivity(),ScanViewActivity.class);
                this.cordova.startActivityForResult(this, intent, 0);
            } else{
                callbackContext.error("error");
            }
        }else{
//            if (message != null && message.length() > 0) {
//                Intent intent=new Intent(this.cordova.getActivity(),lll.com.lworkscan.lib.CaptureActivity.class);
//                this.cordova.startActivityForResult(this, intent, 0);
//            } else{
//                callbackContext.error("error");
//            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(resultCode== 121){
            String barCode=intent.getStringExtra("barCode");
            context.success(barCode);
        }else if(resultCode==122){
            context.success("cancel");
        }else if(resultCode==123){
            context.error("ypNewLand");
        }
    }
}

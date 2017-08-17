package com.andy.jinritoutiao;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.library.base.BaseActivity;
import com.andy.library.image.Image;
import com.andy.library.net.MyCallback;
import com.andy.library.net.Net;
import com.orhanobut.logger.Logger;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.image_logo)
    private ImageView userLogo;
    @Override
    protected void initView() {
        title.setText("主页");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout,new HomeFragment())
                .commit();
        
        userLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
    }

    private void uploadImage() {
        Intent intent = new Intent(MainActivity.this, PhotoSelectorActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("limit",3);//number是选择图片的数量
        startActivityForResult(intent, 0);
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            if(data!=null){
                List<String> list = (List<String>) data.getExtras().getSerializable("photos");

                upload(list);
            }
        }
    }

    private void upload(List<String> list) {
        String urlPath = "http://169.254.251.53:8080/1511k/servlet/UploadServlet";
        Net.upload(urlPath, null, list, new MyCallback<UploadRes>() {
            @Override
            public void success(UploadRes uploadRes) {
                List<String> listUrls = uploadRes.getImages();
                if(listUrls!=null && listUrls.size()>0){
                    String url = listUrls.get(0);
                    Image.display(url,userLogo);
                }
            }

            @Override
            public void fail(Throwable ex) {
                Logger.e(ex.getMessage());
            }
        });
    }


}

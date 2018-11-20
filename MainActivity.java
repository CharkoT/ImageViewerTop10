package charko.tester01.com.imageviewertop10;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import charko.tester01.com.imageviewertop10.Adapter.ImageAdapter;
import charko.tester01.com.imageviewertop10.Contract.MainContract;
import charko.tester01.com.imageviewertop10.Data.Image;
import charko.tester01.com.imageviewertop10.Presenter.MainPresenter;
import charko.tester01.com.imageviewertop10.retrofit.FileUtils;
import charko.tester01.com.imageviewertop10.retrofit.RetrofitClient;
import charko.tester01.com.imageviewertop10.retrofit.RetrofitService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private MainPresenter mainPresenter;
    private MainActivityComponent component;
//    private boolean isPaintingListView = false;

    //    @BindView(R.id.image_lv)
    ListView listView;
    ImageView ivIviv;

    private ImageAdapter imgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // test


//        ButterKnife.bind(this);
        listView = (ListView) findViewById(R.id.image_lv);
        ivIviv = (ImageView) findViewById(R.id.iviv_iv);

        component = DaggerMainActivityComponent.builder()
                .presenterModule(new PresenterModule(new MainPresenter(), this))
                .build();
//        mainPresenter = new MainPresenter();
//        mainPresenter.attachView(this);

//        List<String> list = new List<String>();
//        ArrayList<String>
//        Map<int, String> map = new Map<int, String>();

        RetrofitService service = RetrofitClient.getInstance().getService();
        User user = new User();

        user.setsLoginID("eeodyd@gmail.com");

        Call<String> call = service.getUser(user);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e(">>>>>>>>>>>>>>>>>>>", "@@@@@ >>>> : " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(">>>>>>>>>>>>>>>>>>>", "@@@@@ >>>> : " + t.getCause());

            }
        });

        imgAdapter = new ImageAdapter();
//        component.mainPresenter().updateImage(this, 15);
        listView.setAdapter(imgAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Call<Content> call2 = service.getContentOne(195);

        call2.enqueue(new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
                Log.e(">>>>>>>>>>>>>>>>>>>", ">>>> : " + response.body());

                byte[] btImage = response.body().getBtImage();
                if (btImage != null) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(btImage, 0, btImage.length);

                    imgAdapter.addImage(bmp);
                    imgAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {
                Log.e(">>>>>>>>>>>>>>>>>>>", "eee >>>> : " + t.getCause());

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), 1004);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int firstVisibleItem;
            private boolean lastItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//                    if (lastItem) {
//                        component.mainPresenter().updateImage(getApplicationContext(), 15);
//                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.e(">>>>>>>>>>>>>>>>>>", "firstVisibleItem : " + firstVisibleItem);
//                Log.e(">>>>>>>>>>>>>>>>>>", "visibleItemCount : " + visibleItemCount);
//                Log.e(">>>>>>>>>>>>>>>>>>", "totalItemCount : " + totalItemCount);
//                Log.e(">>>>>>>>>>>>>>>>>>", "isPaintingListView : " + isPaintingListView);

                if ((totalItemCount - 3 < firstVisibleItem + visibleItemCount) && firstVisibleItem != 0) {
                    lastItem = true;
//                    if (!isPaintingListView) {
//                        isPaintingListView = true;
//
//                        component.mainPresenter().updateImage(getApplicationContext(), 15);
//                    }
                } else
                    lastItem = false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                OnManualGetImage();
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 10:
                    byte[] bytes = (byte[]) msg.obj;
                    Bitmap bm = BitmapFactory.decodeByteArray(bytes,
                            0, bytes.length);
                    Glide.with(getApplicationContext()).load(bm).into(ivIviv);
                    break;
            }
        }
    };

    private void OnManualGetImage() {
        InputStream inputStream = null;
        String result = "";
        Message msg = mHandler.obtainMessage();

        try {
            org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 1000 * 3);
            HttpConnectionParams.setSoTimeout(httpclient.getParams(), 1000 * 5);

            HttpPost httpPost = new HttpPost("http://192.168.0.2:8080" + "/content/picture/3");
//            httpPost.setHeader("Accept", "application/json;charset=UTF-8");
//            httpPost.setHeader("Content-type", "application/json;charset=UTF-8");

            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();

            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);

                JSONObject jObject = new JSONObject(result);
                int id = (int) jObject.get("nImageLinkID");
                String btImage = (String) jObject.get("btImage");

                byte[] encodeByte = Base64.decode(btImage, Base64.DEFAULT);


                if (id >= 0) {
                    msg.what = 10;
                    msg.obj = encodeByte;
                    mHandler.sendMessage(msg);
                } else {

                }
            } else
                result = "Did not work!";

        } catch (RuntimeException e) {
            Log.e(">>>>>>>>", ">>>>>>>>>>>>>>>>>>>" + e.getMessage());
        } catch (Exception e) {
            Log.e(">>>>>>>>", ">>>>>>>>>>>>>>>>>>>" + e.getMessage());
        }
    }


    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1004) {
                Uri selectedImageUri = data.getData();

                uploadFile(selectedImageUri);
//                    InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
//
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    options.inSampleSize = 4;
//                    Bitmap bitmap = BitmapFactory.decodeStream(iStream, new Rect(0, 0, 500, 500), options);
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//
//                    byte[] byteArray = stream.toByteArray();
//
//
//                    Log.e(">>>>>>>>>>>>>>>>>>>", "onActivityResult >>>> : ");
//
//                    RetrofitService service = RetrofitClient.getInstance().getService();
//                    Content content = new Content();
//
//                    content.setnWriterID(45);
//                    content.setsWriterNick("테스트용");
//                    content.setBtImage(byteArray);
//                    content.setsAffect("구입/이용 빈도가 줄었다");
//                    content.setsCag1("편의점·마트");
//                    content.setsCag2("GS25");
//                    content.setsCompanyBranch("난곡점");
//                    content.setsProduct("youus 도시락 '고기진짜많구나'");
//                    content.setsUid("-L7xO6WxlpRHKeaj3Hbm");
//                    content.setsContent("테스트 테스트 진짜 테스트 여기 테스트 요기 테스트 이것 테스트");
//
//                    Call<ResponseItem> call = service.setContent(content);
//
//                    call.enqueue(new Callback<ResponseItem>() {
//                        @Override
//                        public void onResponse(Call<ResponseItem> call, Response<ResponseItem> response) {
//                            Log.e(">>>>>>>>>>>>>>>>>>>", ">>>>1111 : " + response.body().getCause());
//                            Log.e(">>>>>>>>>>>>>>>>>>>", ">>>>2222 : " + response);
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseItem> call, Throwable t) {
//                            Log.e(">>>>>>>>>>>>>>>>>>>", ">>>>3333 : " + t.getMessage());
//                            Log.e(">>>>>>>>>>>>>>>>>>>", ">>>>3333 : " + t.getCause());
//
//                        }
//                    });

            }
        }
    }

    private void uploadFile(Uri fileUri) {
        // create upload service client
        RetrofitService service = RetrofitClient.getInstance().getService();

        // create RequestBody instance from file
        File examFile = FileUtils.getFile(this, fileUri);

        byte[] file = getPicture(fileUri, examFile.length());
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(fileUri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", examFile.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, descriptionString);

        // finally, execute the request
        Call<ResponseItem> call = service.upload(178, body);
        call.enqueue(new Callback<ResponseItem>() {
            @Override
            public void onResponse(Call<ResponseItem> call,
                                   Response<ResponseItem> response) {
                Log.e("Upload", "success : " + response.body().getCause());

            }

            @Override
            public void onFailure(Call<ResponseItem> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    private byte[] getPicture(Uri fileUri, long fileSize) {
        InputStream iStream = null;
//            iStream = getContentResolver().openInputStream(fileUri);
//
//            BitmapFactory.Options options = new BitmapFactory.Options();
//
//            int size = iStream.available();
//
//            if (size > 1024 * 1024)
//                options.inSampleSize = 4;
//
//            Bitmap bitmap = BitmapFactory.decodeStream(iStream, new Rect(0, 0, 300, 300), options);
        Bitmap bitmap = resize(fileUri, 500);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        return stream.toByteArray();

    }

    private Bitmap resize(Uri uri, int resize) {
        Bitmap resizeBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options); // 1번

            int width = options.outWidth;
            int height = options.outHeight;
            int samplesize = 1;

            while (true) {//2번
                if (width / 2 < resize || height / 2 < resize)
                    break;
                width /= 2;
                height /= 2;
                samplesize *= 2;
            }

            options.inSampleSize = samplesize;
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options); //3번
            resizeBitmap = bitmap;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resizeBitmap;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        component.mainPresenter().detachView();
    }

    @Override
    public void addItems(ArrayList<Image> images) {
//        imgAdapter.setImage(images);
    }

    @Override
    public void notifyAdapter() {
//        imgAdapter.notifyDataSetChanged();
//        isPaintingListView = false;
    }

    public String convertInputStreamToString(InputStream is) {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

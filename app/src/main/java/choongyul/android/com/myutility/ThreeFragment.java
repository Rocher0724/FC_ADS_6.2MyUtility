package choongyul.android.com.myutility;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {
    WebView webView;
    View view;

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // holder처리
        if(view!=null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_three, container, false);

        webView = (WebView) view.findViewById(R.id.webView);

        //자바스크립트 사용하도록 설정. - 필수설정
        webView.getSettings().setJavaScriptEnabled(true);

        // zoom 사용 설정 - 선택적
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);


        // 웹뷰 클라이언트를 지정  - 크롬
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl("http://google.com");

        return view;
    }

    public boolean goBack() {
        if(webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            return false;
        }
    }
}

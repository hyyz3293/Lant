package com.jack.lant.ui.network;

public class TrustAllTrustManager implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {


//    //  直接通过主机认证
//    HostnameVerifier hv = new HostnameVerifier() {
//        public boolean verify(String urlHostName, SSLSession session) {
//            return true;
//        }
//    };
//    //  配置认证管理器
//    javax.net.ssl.TrustManager[] trustAllCerts = {new TrustAllTrustManager()};
//    SSLContext sc = SSLContext.getInstance("SSL");
//    SSLSessionContext sslsc = sc.getServerSessionContext();
//sslsc.setSessionTimeout(0);
//sc.init(null, trustAllCerts, null);
//HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
////  激活主机认证
//HttpsURLConnection.setDefaultHostnameVerifier(hv);


    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
        return true;
    }

    public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
        return true;
    }

    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
            throws java.security.cert.CertificateException {
        return;
    }

    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
            throws java.security.cert.CertificateException {
        return;
    }

}
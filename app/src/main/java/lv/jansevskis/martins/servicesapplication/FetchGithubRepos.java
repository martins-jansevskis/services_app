package lv.jansevskis.martins.servicesapplication;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchGithubRepos extends AsyncTask<String, Void, String> {
    FetchCompleteListener mFetchCompleteListener;

    public FetchGithubRepos(FetchCompleteListener fetchCompleteListener) {
        this.mFetchCompleteListener = fetchCompleteListener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return downloadData(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 2
    @Override
    protected void onPostExecute(String result) {
        try {
            mFetchCompleteListener.downloadComplete(Helpers.retrieveRepositoriesFromResponse(result));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String downloadData(String urlString) throws IOException {
        InputStream istream = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            istream = connection.getInputStream();
            return convertToString(istream);
        } finally {
            if (istream != null) {
                istream.close();
            }
        }
    }

    private String convertToString(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return new String(total);
    }
}

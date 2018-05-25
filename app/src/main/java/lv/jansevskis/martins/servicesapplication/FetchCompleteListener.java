package lv.jansevskis.martins.servicesapplication;

import java.util.ArrayList;

public interface FetchCompleteListener {
    void downloadComplete(ArrayList<Repository> repositories);
}

package cc.travel.com.cctravel;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ola_ev.cctravlel.R;

/**
 * Created by GW00070468 on 2017/10/24.
 */

public class Utils {


    public static void initForMessageCenterIcon(Context context,NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (item.getItemId() == R.id.order_info) {
                RedPointDrawable redPointDrawable = RedPointDrawable.wrap(context, item.getIcon());
                redPointDrawable.setShowRedPoint(true);
                redPointDrawable.setGravity(Gravity.LEFT);
                item.setIcon(redPointDrawable);
                // 把drawable添加到我们的成员变量中去，以便后面直接对它进行设置
                //mRedPointView.addRedPointDrawable(redPointDrawable);
            }
        }
    }
}

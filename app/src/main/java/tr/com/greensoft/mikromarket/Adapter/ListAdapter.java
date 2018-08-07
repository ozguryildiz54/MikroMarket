package tr.com.greensoft.mikromarket.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tr.com.greensoft.mikromarket.Model.Urun;
import tr.com.greensoft.mikromarket.R;

/**
 * Created by Özgür on 10.07.2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListeHolder>  {

    private List<Urun> mUrun;
    private Context mContext;
    private Activity activity;

    public ListAdapter(List<Urun> urun, Context context){ // Bu sınıfın yapılandırıcı metodu
        mUrun = urun;
        mContext = context; // Gelen sayfa bilgisini alır.
    }

    @Override
    public ListeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View v = layoutInflater.inflate(R.layout.activity_listele,parent,false);
        return new ListeHolder(v,mContext);
    }

    @Override
    public void onBindViewHolder(ListeHolder holder, int position) {
        Urun urun = mUrun.get(position);
        holder.bindHolder(urun);
    }

    @Override
    public int getItemCount() {
        return mUrun.size();
    }
}

class ListeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView urunAdi;
    private TextView barkod;
    private TextView fiyat;
    private Urun murun;
    private Context mContext;


    public ListeHolder(View itemView,Context context) {
        super(itemView);
        itemView.setOnClickListener(this);
        urunAdi = (TextView) itemView.findViewById(R.id.adSoyad);
        barkod = (TextView) itemView.findViewById(R.id.mail);
        fiyat = (TextView) itemView.findViewById(R.id.no);
        mContext = context;
    }

    public void bindHolder(Urun urun) {
        urunAdi.setText(urun.getUrunAdi());
        barkod.setText(urun.getBarkod());
        fiyat.setText((int) urun.getFiyat());

        murun = urun;
    }

    @Override
    public void onClick(View view) { // Liste üzerinde tıklanıldığında yapılacak işlemler.
        /*Intent i = new Intent(mContext, DetaylarActivity.class);
        i.putExtra("id",mkisi.getId()); // tıklanıldığında id bilgisi "id" parametresi ile gönderilir.
        ((Activity) mContext).startActivityForResult(i,2); // Detaylar sayfası açılır. Parametre olarak verilen 2 değeri ise detaylar sayfasından geldiğimizi anlamak için yazılmıştır.
        // Bunun kontrolü ise main sınıfında activityForResult bloğunda yapılır
        */
    }
}

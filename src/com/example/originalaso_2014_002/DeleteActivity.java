package com.example.originalaso_2014_002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * @author masatoge
 */

public class DeleteActivity extends Activity implements
	View.OnClickListener,AdapterView.OnItemClickListener{

	//SQLiteデータベース空間を操作するインスタンス変数を宣言
			SQLiteDatabase sdb = null;
			//MySQLiteOpenHelperを操作するインスタンス変数を宣言
			MySQLiteOpenHelper helper = null;
			
			//リストにて選択したHitokotoテーブルのレコードの[_id]カラム値を保存する変数の宣言
			int selectedID = -1;
			//リストにて選択した行番号を保持する変数の宣言
			int lastPosition = -1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);
	}
	 @Override
	 protected void onResume(){
		 super.onResume();
		 
		 //各view部品を操作するidを取得
		 Button btnDelete = (Button)findViewById(R.id.button2);
		 Button btnMainte_Back = (Button)findViewById(R.id.button1);
		 ListView lstHitokoto = (ListView)findViewById(R.id.listView1);
		 
		 //各ButtonにOnClickListenerをセット
		 btnDelete.setOnClickListener(this);
		 btnMainte_Back.setOnClickListener(this);
		 
		 //ListViewにOnItemClickListenerをセット
		 lstHitokoto.setOnItemClickListener(this);
		 
		 //ListViewにDBの値をセット
		 this.setDBValuetoList(lstHitokoto);
		 
	 }
	
	 /**
	  * 引数のListViewにDBのデータをセット
	  * @param lstHitokoto 対象となるListView
	  */
	 private void setDBValuetoList(ListView lstHitokoto){
		 
		 SQLiteCursor cursor = null;
		 
		 //クラスのフィールド変数がNULLなら、データベース空間オープン
		 if(sdb==null){
			 helper = new MySQLiteOpenHelper(getApplicationContext());
		 }
		 try{
			 sdb=helper.getWritableDatabase();
		 }catch(SQLiteException e){
			 //異常終了
			 Log.e("ERROR", e.toString());
		 }
		 //MySQLiteOpenHelperにSELECT文を実行させて結果のカーソルを受ける
		 cursor = this.helper.selectHitokotoList(sdb);
		 
		 //dblayout:ListViewにさらにレイアウトを指定するもの
		 int db_layout = android.R.layout.simple_list_item_activated_1;
		 //form:カーソルからListViewに指定するカラムの値を指定するもの
		 String[]from = {"phrase"};
		 //to:Listviewの中に指定したdb_layoutに配置する、各行のview部品のid
		 int[] to = new int[]{android.R.id.text1};
		 
		 //ListViewにセットするアダプターを生成
		 //カーソルをもとに、fromの列から、toのViewへ値のマッピングが行われる。
		 SimpleCursorAdapter adapter =
				 new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);
		 
		 //アダプターを設定します
		 lstHitokoto.setAdapter(adapter);
	 }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}


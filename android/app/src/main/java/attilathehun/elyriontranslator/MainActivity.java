package attilathehun.elyriontranslator;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Button;
import android.widget.CheckBox;
import android.media.MediaPlayer;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.CompoundButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;

public class MainActivity extends Activity {
	
	private String legal_alphabet = "";
	private String elyrion_alphabet = "";
	private String input = "";
	private String output = "";
	private double x = 0;
	private double y = 0;
	private String s1 = "";
	private String s2 = "";
	private String elyrion_decrytion = "";
	private double random = 0;
	private double z = 0;
	private boolean dragonborn_comes = false;
	
	private ArrayList<String> muhuhehe = new ArrayList<>();
	private ArrayList<String> quotes = new ArrayList<>();
	
	private LinearLayout linear4;
	private LinearLayout linear1;
	private TextView textview2;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private EditText input_textfield;
	private ImageView delete;
	private Switch switch1;
	private TextView textview1;
	private Button copy;
	private Button translateb;
	private EditText output_textfield;
	private TextView textview3;
	private CheckBox ignorebox;
	private CheckBox onlyvalid;
	private CheckBox playelimusic;
	private CheckBox elyrionmode;
	private LinearLayout linear6;
	private LinearLayout hidden_linear;
	
	private MediaPlayer tribe_music_player;
	private SharedPreferences file;
	private AlertDialog.Builder about_dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear4 = findViewById(R.id.linear4);
		linear1 = findViewById(R.id.linear1);
		textview2 = findViewById(R.id.textview2);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		input_textfield = findViewById(R.id.input_textfield);
		delete = findViewById(R.id.delete);
		switch1 = findViewById(R.id.switch1);
		textview1 = findViewById(R.id.textview1);
		copy = findViewById(R.id.copy);
		translateb = findViewById(R.id.translateb);
		output_textfield = findViewById(R.id.output_textfield);
		textview3 = findViewById(R.id.textview3);
		ignorebox = findViewById(R.id.ignorebox);
		onlyvalid = findViewById(R.id.onlyvalid);
		playelimusic = findViewById(R.id.playelimusic);
		elyrionmode = findViewById(R.id.elyrionmode);
		linear6 = findViewById(R.id.linear6);
		hidden_linear = findViewById(R.id.hidden_linear);
		file = getSharedPreferences("f", Activity.MODE_PRIVATE);
		about_dialog = new AlertDialog.Builder(this);
		
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				input_textfield.setText("");
			}
		});
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					input_textfield.setHint("Enter text in ∑∫ỹriȱŋ language");
					if (elyrionmode.isChecked()) {
						input_textfield.setHint("∑ŋŦ∑r Ŧ∑eŦ iŋ ∑∫ỹriȱŋ ∫aŋþ₺aþ∑");
					}
				}
				else {
					input_textfield.setHint("Enter text in your language");
					if (elyrionmode.isChecked()) {
						input_textfield.setHint("∑ŋŦ∑r Ŧ∑eŦ iŋ ỹȱ₺r ∫aŋþ₺aþ∑");
					}
				}
			}
		});
		
		copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (output_textfield.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Navalons on you!");
				}
				else {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", output_textfield.getText().toString()));
					SketchwareUtil.showMessage(getApplicationContext(), output_textfield.getText().toString());
				}
			}
		});
		
		translateb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (input_textfield.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Dragons!");
				}
				else {
					dragonborn_comes = false;
					_translate(input_textfield.getText().toString().trim().toLowerCase(), switch1.isChecked());
				}
			}
		});
		
		output_textfield.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				input_textfield.requestFocus();
			}
		});
		
		output_textfield.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!_charSeq.equals(output)) {
					output_textfield.setText(output);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		ignorebox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					onlyvalid.setChecked(false);
				}
			}
		});
		
		onlyvalid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					ignorebox.setChecked(false);
				}
			}
		});
		
		playelimusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					if (z == 1) {
						tribe_music_player.start();
					}
					else {
						tribe_music_player = MediaPlayer.create(getApplicationContext(), R.raw.elyrion_tribe_music);
						tribe_music_player.setLooping(true);
						tribe_music_player.start();
						z = 1;
					}
				}
				else {
					tribe_music_player.pause();
				}
			}
		});
		
		elyrionmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				dragonborn_comes = true;
				if (_isChecked) {
					setTitle("∑∫ỹriȱŋ Ŧraŋ^∫aŦȱr");
					textview2.setText("Ŧraŋ^∫aŦ∑ Ŧ∑eŦ");
					switch1.setText("ŋȱŦ ∑∫ỹriȱŋ");
					copy.setText("#ȱπỹ");
					translateb.setText("Ŧraŋ^∫aŦ∑");
					textview3.setText("ȱπŦiȱŋ^");
					if (switch1.isChecked()) {
						input_textfield.setHint("∑ŋŦ∑r Ŧ∑eŦ iŋ ∑∫ỹriȱŋ ∫aŋþ₺aþ∑");
					}
					else {
						input_textfield.setHint("∑ŋŦ∑r Ŧ∑eŦ iŋ ỹȱ₺r ∫aŋþ₺aþ∑");
					}
					input = output_textfield.getHint().toString();
					_translate(input.toLowerCase(), false);
					output_textfield.setHint(output);
					playelimusic.setText("π∫aỹ ∑∫ỹriȱŋ m₺^i#");
					onlyvalid.setText("∑e#∫₺Δ∑ iŋ‡a∫iΔ^ #ţara#Ŧ∑r^");
					elyrionmode.setText("∑∫ỹriȱŋ mȱΔ∑");
					ignorebox.setText("iþŋȱr∑ iŋ‡a∫iΔ #ţara#Ŧ∑r^");
				}
				else {
					setTitle("∑∫ỹriȱŋ Translator");
					textview2.setText("Translate text");
					switch1.setText("Not ∑∫ỹriȱŋ");
					copy.setText("Copy");
					translateb.setText("Translate");
					textview3.setText("Options");
					if (switch1.isChecked()) {
						input_textfield.setHint("Enter text in ∑∫ỹriȱŋ language");
					}
					else {
						input_textfield.setHint("Enter text in your language");
					}
					input = output_textfield.getHint().toString();
					_translate(input.toLowerCase(), true);
					output_textfield.setHint(output.toUpperCase().substring((int)(0), (int)(1)).concat(output.substring((int)(1), (int)(output.length()))));
					ignorebox.setText("Ignore invalid characters");
					onlyvalid.setText("Exclude invalid characters");
					playelimusic.setText("Play ∑∫ỹriȱŋ music");
					elyrionmode.setText("∑∫ỹriȱŋ mode");
				}
			}
		});
		
		hidden_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (elyrionmode.isChecked()) {
					about_dialog.setTitle("∑∫ỹriȱŋ Ŧraŋ^∫aŦȱr");
					about_dialog.setMessage("Ŧţaŋ§^ ₼ȱr ₺^iŋþ mỹ aππ <3\n\n‡∑r^iȱŋ 1.0 oỹ aŦŦi∫aŦţ∑ţ₺ŋ");
					about_dialog.setPositiveButton("∫i#§ iŦ", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
				}
				else {
					about_dialog.setTitle("∑∫ỹriȱŋ Translator");
					about_dialog.setMessage("Thanks for using my app <3\n\nversion 1.0 by AttilaTheHun");
					about_dialog.setPositiveButton("Lick It", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
				}
				about_dialog.setCancelable(false);
				about_dialog.create().show();
			}
		});
	}
	
	private void initializeLogic() {
		setTitle("∑∫ỹriȱŋ Translator");
		legal_alphabet = " \nabcdefghijklmnopqrstuvwxyz,.'\"!?()@$_&/*1234567890";
		elyrion_alphabet = " \nao#Δ∑₼þţiƒ§∫mŋȱπ¦r^Ŧ₺‡~eỹΩ,.'\"!?()@$_&/*1234567890";
		elyrion_decrytion = " \nao#δ∑₼þţiƒ§∫mŋȱπ¦r^ŧ₺‡~eỹω,.'\"!?()@$_&/*1234567890";
		quotes.add("To know your enemy, you must become your enemy.");
		quotes.add("One often meets his destiny on the way he takes to avoid it.");
		quotes.add("Yesterday is a history, tommorow is a mystery, but today is a gift. That's why we call it the present.");
		quotes.add("Sharing tea with a fascinating stranger is one of life's true delights.");
		quotes.add("The supreme art of war is to subdue the enemy without fighting.");
		quotes.add("Appear weak when you are strong, and strong when you are weak.");
		random = SketchwareUtil.getRandom((int)(0), (int)(quotes.size() - 1));
		output_textfield.setHint(quotes.get((int)(random)));
		_loadData();
	}
	
	@Override
	public void onBackPressed() {
		_saveData();
		finish();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		_saveData();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		_saveData();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		_saveData();
	}
	public void _translate(final String _input, final boolean _language) {
		input = _input;
		output = "";
		x = 0;
		if (_language) {
			s1 = elyrion_decrytion;
			s2 = legal_alphabet;
		}
		else {
			s1 = legal_alphabet;
			s2 = elyrion_alphabet;
		}
		try{
			if (ignorebox.isChecked()) {
				for(int  i = 0; i < input.length(); i++) {
					
									y = s1.indexOf(input.substring((int)x, (int)x + 1));
						
						if(y == -1){
						output = output.concat(input.substring((int)x, (int)x + 1));
					}else{
							output = output.concat(s2.substring((int)y,(int) y + 1));
					}
									
									x++;
					
							}
			}
			else {
				if (onlyvalid.isChecked()) {
					for(int  i = 0; i < input.length(); i++) {
						
										y = s1.indexOf(input.substring((int)x, (int)x + 1));
							
							if(y != -1){
								output = output.concat(s2.substring((int)y,(int) y + 1));
						}
										
										x++;
						
								}
				}
				else {
					for(int  i = 0; i < input.length(); i++) {
						
										y = s1.indexOf(input.substring((int)x, (int)x + 1));
							
							
							output = output.concat(s2.substring((int)y,(int) y + 1));
						
										
										x++;
						
								}
				}
			}
			if (!dragonborn_comes) {
				output_textfield.setText(output);
			}
		}catch(Exception e){
			SketchwareUtil.showMessage(getApplicationContext(), "Some unhandlable characters appeared. Try to remove them");
		}
	}
	
	
	public void _saveData() {
		if (ignorebox.isChecked()) {
			file.edit().putString("ignore", "true").commit();
		}
		else {
			file.edit().putString("ignore", "false").commit();
		}
		if (onlyvalid.isChecked()) {
			file.edit().putString("onlyvalid", "true").commit();
		}
		else {
			file.edit().putString("onlyvalid", "false").commit();
		}
		if (playelimusic.isChecked()) {
			file.edit().putString("playmusic", "true").commit();
		}
		else {
			file.edit().putString("playmusic", "false").commit();
		}
		if (elyrionmode.isChecked()) {
			file.edit().putString("elyrionmode", "true").commit();
		}
		else {
			file.edit().putString("elyrionmode", "false").commit();
		}
	}
	
	
	public void _loadData() {
		if (!file.getString("ignore", "").equals("")) {
			if (file.getString("ignore", "").equals("true")) {
				ignorebox.setChecked(true);
			}
		}
		if (!file.getString("onlyvalid", "").equals("")) {
			if (file.getString("onlyvalid", "").equals("true")) {
				onlyvalid.setChecked(true);
			}
		}
		if (!file.getString("playmusic", "").equals("")) {
			if (file.getString("playmusic", "").equals("true")) {
				playelimusic.setChecked(true);
			}
		}
		if (!file.getString("elyrionmode", "").equals("")) {
			if (file.getString("elyrionmode", "").equals("true")) {
				elyrionmode.setChecked(true);
			}
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
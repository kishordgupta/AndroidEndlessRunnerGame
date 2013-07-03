package com.rhymes.client.mariobros.gamehistory;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.rhymes.client.gp.pigshotclone.R;

public class SoundManager {

	static private SoundManager _instance;
	private static SoundPool mSoundPool;
	private static HashMap<Integer, Integer> mSoundPoolMap;
	private static AudioManager mAudioManager;
	private static Context mContext;
	public static boolean musicFlag = true;

	private SoundManager() {
	}

	/**
	 * Requests the instance of the Sound Manager and creates it if it does not
	 * exist.
	 * 
	 * @return Returns the single instance of the SoundManager
	 */
	static synchronized public SoundManager getInstance() {
		if (_instance == null)
			_instance = new SoundManager();
		return _instance;
	}

	/**
	 * Initialises the storage for the sounds
	 * 
	 * @param theContext
	 *            The Application context
	 */
	public static void initSounds(Context theContext) {
		mContext = theContext;
		mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		mSoundPoolMap = new HashMap<Integer, Integer>();
		mAudioManager = (AudioManager) mContext
				.getSystemService(Context.AUDIO_SERVICE);
	}

	/**
	 * Add a new Sound to the SoundPool
	 * 
	 * @param Index
	 *            - The Sound Index for Retrieval
	 * @param SoundID
	 *            - The Android ID for the Sound asset.
	 */
	public static void addSound(int Index, int SoundID) {
		mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));
	}

	/**
	 * Loads the various sound assets Currently hardcoded but could easily be
	 * changed to be flexible.
	 */
	public static void loadSounds() {
		// escape_from_rocket 1
		// enter_to_rocket 2
		// button_click 3
		// starcollect 4
		// slipper_water 5
		// inturrupted_by_other_object 6
		// gas_collector 7
		// char_tapped_on_touch 8
		// menu 9
		// destroy_ and_gameover_show_score 10
		

		// SoundManager.playSound(1, 1);

		mSoundPoolMap.put(1, mSoundPool.load(mContext, R.raw.escape_from_rocket, 1));
		mSoundPoolMap.put(2, mSoundPool.load(mContext, R.raw.enter_to_rocket, 1));
		mSoundPoolMap.put(3, mSoundPool.load(mContext, R.raw.button_click, 1));
		mSoundPoolMap.put(4, mSoundPool.load(mContext, R.raw.starcollect, 1));
		mSoundPoolMap.put(5, mSoundPool.load(mContext, R.raw.slipper_water, 1));
		mSoundPoolMap.put(6, mSoundPool.load(mContext, R.raw.inturrupted_by_other_object, 1));
		mSoundPoolMap.put(7, mSoundPool.load(mContext, R.raw.gas_collector, 1));
		// mSoundPoolMap.put(8, mSoundPool.load(mContext, R.raw.buttonclick,
		// 1));//unused
		// mSoundPoolMap.put(9, mSoundPool.load(mContext, R.raw.buttonclick,
		// 1));//unused
		// mSoundPoolMap.put(10, mSoundPool.load(mContext, R.raw.buttonclick,
		// 1));//unused
		mSoundPoolMap.put(8, mSoundPool.load(mContext, R.raw.char_tapped_on_touch, 1));
//		mSoundPoolMap.put(9, mSoundPool.load(mContext, R.raw.menu,1));
		mSoundPoolMap.put(10, mSoundPool.load(mContext, R.raw.destroy_and_gameover_show_score, 1));
		// mSoundPoolMap.put(14, mSoundPool.load(mContext, R.raw.buttonclick,
		// 1)); // 14 unused

		// mSoundPoolMap.put(4, mSoundPool.load(mContext, R.raw.coffinenter,
		// 1));
		// mSoundPoolMap.put(5, mSoundPool.load(mContext, R.raw.destroy, 1));
		// mSoundPoolMap.put(6, mSoundPool.load(mContext, R.raw.doormover, 1));
		// mSoundPoolMap.put(7, mSoundPool.load(mContext, R.raw.fallingstar,
		// 1));
		// mSoundPoolMap.put(8, mSoundPool.load(mContext, R.raw.levelcompletion,
		// 1));
		// mSoundPoolMap.put(9, mSoundPool.load(mContext, R.raw.menu, 1));
		// mSoundPoolMap.put(10, mSoundPool.load(mContext, R.raw.play, 1));
		// mSoundPoolMap.put(11, mSoundPool.load(mContext, R.raw.ropecut, 1));
		// mSoundPoolMap.put(12, mSoundPool.load(mContext,
		// R.raw.ropecuttercloud, 1));
		// mSoundPoolMap.put(13, mSoundPool.load(mContext, R.raw.starcollect,
		// 1));
		// mSoundPoolMap.put(14, mSoundPool.load(mContext, R.raw.startstage,
		// 1));

	}

	/**
	 * Plays a Sound
	 * 
	 * @param index
	 *            - The Index of the Sound to be played
	 * @param speed
	 *            - The Speed to play not, not currently used but included for
	 *            compatibility
	 */
	public static void playSound(int index, float speed) {
		if (musicFlag) {
			float streamVolume = mAudioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC);
			streamVolume = streamVolume
					/ mAudioManager
							.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			streamVolume = 1.0f;
			mSoundPool.play(mSoundPoolMap.get(index), streamVolume,
					streamVolume, 1, 0, speed);
		}
	}

	/**
	 * Stop a Sound
	 * 
	 * @param index
	 *            - index of the sound to be stopped
	 */
	public static void stopSound(int index) {
		mSoundPool.stop(mSoundPoolMap.get(index));
	}

	public static void stopAllSound() {
		try {
			for (int i = 0; i < mSoundPoolMap.size(); i++)
				mSoundPool.stop(mSoundPoolMap.get(i));
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	public static void cleanup() {
		mSoundPool.release();
		mSoundPool = null;
		mSoundPoolMap.clear();
		mAudioManager.unloadSoundEffects();
		_instance = null;

	}

}
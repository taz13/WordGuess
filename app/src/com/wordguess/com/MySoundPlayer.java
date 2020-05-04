package com.wordguess.com;

import android.content.Context;
import android.media.SoundPool;
import java.util.HashMap;

public class MySoundPlayer
{
  public static final int S1 = 2130968576;
  private static SoundPool soundPool;
  private static HashMap soundPoolMap;

  public static void initSounds(Context paramContext)
  {
    soundPool = new SoundPool(2, 3, 100);
    soundPoolMap = new HashMap(1);
    soundPoolMap.put(Integer.valueOf(2130968576), Integer.valueOf(soundPool.load(paramContext, 2130968576, 1)));
  }

  public static void playSound(Context paramContext, int paramInt)
  {
    if ((soundPool == null) || (soundPoolMap == null))
      initSounds(paramContext);
    soundPool.play(((Integer)soundPoolMap.get(Integer.valueOf(paramInt))).intValue(), 0.5F, 0.5F, 1, 0, 1.0F);
  }
}

/* Location:           C:\Users\Owner\Desktop\test\classes_dex2jar.jar
 * Qualified Name:     com.word.guess.com.MySoundPlayer
 * JD-Core Version:    0.6.2
 */
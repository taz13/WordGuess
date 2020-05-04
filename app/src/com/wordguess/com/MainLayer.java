package com.wordguess.com;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

public class MainLayer extends CCColorLayer
{
  public static int iScreen = 0;
  CCMenu localCCSound;

  protected MainLayer(ccColor4B paramccColor4B)
  {
    super(paramccColor4B);
    CGSize localCGSize = CCDirector.sharedDirector().displaySize();
    CCSprite localCCSprite = CCSprite.sprite("main_background.png");
    float f1 = localCGSize.width / localCCSprite.getContentSize().width;
    float f2 = localCGSize.height / localCCSprite.getContentSize().height;
    localCCSprite.setAnchorPoint(CGPoint.ccp(0.0F, 0.0F));
    localCCSprite.setPosition(CGPoint.ccp(0.0F, 0.0F));
    localCCSprite.setScaleX(f1);
    localCCSprite.setScaleY(f2);
    addChild(localCCSprite);
    CCMenu localCCMenu = CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("red_button.png", "red_button.png", this, "onButtonClick") });
    //localCCMenu.setPosition(CGPoint.ccp((getWidth() / 2.0F)-(localCCMenu.getContentSize().width*.4f/2.0f), (getHeight() / 2.0F)-(localCCMenu.getContentSize().height*.6f/2)));
    localCCMenu.setScaleX(.85f);
    localCCMenu.setScaleY(.85f);
    localCCMenu.setPosition(CGPoint.ccp(getWidth()/2.0F-40f,getHeight()/2.0F));
    addChild(localCCMenu);
    //System.out.println("Position X: "+getWidth()/2.0F+" Position Y: "+getHeight()/2.0F);
    
//    localCCSound = CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("sound_on.png", "sound_on.png", this, "onSoundClick") });
    //localCCMenu.setPosition(CGPoint.ccp((getWidth() / 2.0F)-(localCCMenu.getContentSize().width*.4f/2.0f), (getHeight() / 2.0F)-(localCCMenu.getContentSize().height*.6f/2)));
    
    if(MainActivity.sound==true)
	  {
		  //MainActivity.sound=true;
		  //this.removeChild(localCCSound, false);
		  localCCSound=CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("sound_on.png", "sound_on.png", this, "onSoundClick") });
		  //addChild(localCCSound);
		 // System.out.println("Sound On");
	  }
	  else
	  {
		 // MainActivity.sound=false;
		 // this.removeChild(localCCSound, false);
		  localCCSound=CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("sound_off.png", "sound_off.png", this, "onSoundClick") });
		  //addChild(localCCSound);
		  //System.out.println("Sound Off");
	  }
    
    localCCSound.setScaleX(.55f);
    localCCSound.setScaleY(.55f);
    localCCSound.setPosition(CGPoint.ccp(getWidth()/4.0f*3.0f-60f,(getHeight()/4.0f*3.0f)-60f));
    addChild(localCCSound);
    setIsTouchEnabled(false);
    iScreen = 1;
  }

  public static CCScene scene()
  {
    CCScene localCCScene = CCScene.node();
    localCCScene.addChild(new MainLayer(ccColor4B.ccc4(0, 0, 0, 255)));
    return localCCScene;
  }

  public void onButtonClick(Object paramObject)
  {
    CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(0.5F, PlayLayer.scene()));
  }
  
  public void onSoundClick(Object paramObject)
  {
	  if(MainActivity.sound==false)
	  {
		  MainActivity.sound=true;
		  this.removeChild(localCCSound, false);
		  localCCSound=CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("sound_on.png", "sound_on.png", this, "onSoundClick") });
		  localCCSound.setScaleX(.55f);
		  localCCSound.setScaleY(.55f);
		  localCCSound.setPosition(CGPoint.ccp(getWidth()/4.0f*3.0f-60f,(getHeight()/4.0f*3.0f)-60f));
		  addChild(localCCSound);
		  System.out.println("Sound On");
	  }
	  else
	  {
		  MainActivity.sound=false;
		  this.removeChild(localCCSound, false);
		  localCCSound=CCMenu.menu(new CCMenuItem[] { CCMenuItemImage.item("sound_off.png", "sound_off.png", this, "onSoundClick") });
		  localCCSound.setScaleX(.55f);
		  localCCSound.setScaleY(.55f);
		  localCCSound.setPosition(CGPoint.ccp(getWidth()/4.0f*3.0f-60f,(getHeight()/4.0f*3.0f)-60f));
		  addChild(localCCSound);
		  System.out.println("Sound Off");
	  }
  }
}

/* Location:           C:\Users\Owner\Desktop\test\classes_dex2jar.jar
 * Qualified Name:     com.word.guess.com.MainLayer
 * JD-Core Version:    0.6.2
 */
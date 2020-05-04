package com.wordguess.com;

import android.view.MotionEvent;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

public class HelpLayer extends CCColorLayer
{
  private PlayLayer _plLayer;
  CCSprite _spr1;
  CCSprite _spr2;
  CCSprite _spr3;
  CCSprite _spr4;
  float sp = 70.0F;
  float topOffset ;
 CGSize winSize;

  protected HelpLayer(ccColor4B paramccColor4B)
  {
    super(paramccColor4B);
    
    this.winSize=CCDirector.sharedDirector().displaySize();
    if(winSize.height>900 && winSize.width>500)
    {
    	topOffset=400;
    }
    else
    {
    	topOffset=200;
    }
    CCSprite localCCSprite = CCSprite.sprite("help_options_background.png");
    float f1 = getWidth() / localCCSprite.getContentSize().width;
    float f2 = getHeight() / localCCSprite.getContentSize().height;
    localCCSprite.setAnchorPoint(CGPoint.ccp(0.0F, 0.0F));
    localCCSprite.setPosition(CGPoint.ccp(0.0F, 0.0F));
    localCCSprite.setScaleX(f1);
    localCCSprite.setScaleY(f2);
    addChild(localCCSprite);
    this._spr1 = CCSprite.sprite("show_one_letter_btn.png");
    float f3 = (getWidth() - 30.0F * 2.0F) / this._spr1.getContentSize().width;
    this._spr1.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - this.topOffset));
    this._spr1.setScale(f3);
    addChild(this._spr1);
    this._spr2 = CCSprite.sprite("share_facebook_btn.png");
    float f4 = (getWidth() - 30.0F * 2.0F) / this._spr2.getContentSize().width;
    this._spr2.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - this.topOffset - f3 * this._spr1.getContentSize().height - this.sp));
    this._spr2.setScale(f3);
    addChild(this._spr2);
    
    this._spr3 = CCSprite.sprite("help_ask_twitter_btn.png");
    float f5 = (getWidth() - 30.0F * 2.0F) / this._spr3.getContentSize().width;
    this._spr3.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - this.topOffset - f3 * this._spr1.getContentSize().height - f4 * this._spr2.getContentSize().height-this.sp));
    this._spr3.setScale(f3);
    addChild(this._spr3);
    
    this._spr4 = CCSprite.sprite("help_ask_Google_btn.png");
    //float f5 = (getWidth() - 30.0F * 2.0F) / this._spr3.getContentSize().width;
    this._spr4.setPosition(CGPoint.ccp(getWidth() / 2.0F, getHeight() - this.topOffset - f3 * this._spr1.getContentSize().height - f4 * this._spr2.getContentSize().height-f5 * this._spr3.getContentSize().height-this.sp));
    this._spr4.setScale(f3);
    addChild(this._spr4);
    
    this._spr1.setVisible(false);
    this._spr2.setVisible(false);
    this._spr3.setVisible(false);
    this._spr4.setVisible(false);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setIsTouchEnabled(true);
  }

  public boolean ccTouchesEnded(MotionEvent paramMotionEvent)
  {
    CGPoint localCGPoint = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(paramMotionEvent.getX(), paramMotionEvent.getY()));
    if ((this._spr1.getVisible()) && (CGRect.containsPoint(this._spr1.getBoundingBox(), localCGPoint)))
      onClickButton1();
    if ((this._spr2.getVisible()) && (CGRect.containsPoint(this._spr2.getBoundingBox(), localCGPoint)))
      onClickFaceB();
    if ((this._spr3.getVisible()) && (CGRect.containsPoint(this._spr3.getBoundingBox(), localCGPoint)))
        onClickTwitter();
    if ((this._spr4.getVisible()) && (CGRect.containsPoint(this._spr4.getBoundingBox(), localCGPoint)))
        onClickGplus();
    return true;
  }

  private void onClickTwitter() {
	// TODO Auto-generated method stub
	  System.out.println("Inside onclickTwitter on HelpLayer");
	    this._spr1.setVisible(false);
	    this._spr2.setVisible(false);
	    this._spr3.setVisible(false);
	    this._spr4.setVisible(false);
	    setPosition(CGPoint.ccp(getWidth(), 0.0F));
	    setVisible(false);
	    this._plLayer.onShareTwitter();
}

public void onClickGplus()
{
	System.out.println("Inside onclickgplus on HelpLayer");
    this._spr1.setVisible(false);
    this._spr2.setVisible(false);
    this._spr3.setVisible(false);
    this._spr4.setVisible(false);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setVisible(false);
   // this._plLayer.onShareGplus();
}
  
public void onClickButton1()
  {
    this._spr1.setVisible(false);
    this._spr2.setVisible(false);
    this._spr3.setVisible(false);
    this._spr4.setVisible(false);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setVisible(false);
    this._plLayer.onShowOneLetter();
  }

  public void onClickFaceB()
  {
	  System.out.println("Inside onclickFaceB on HelpLayer");
    this._spr1.setVisible(false);
    this._spr2.setVisible(false);
    this._spr3.setVisible(false);
    this._spr4.setVisible(false);
    setPosition(CGPoint.ccp(getWidth(), 0.0F));
    setVisible(false);
    this._plLayer.onShareFB();
  }
  
  

  public void setParentLayer(PlayLayer paramPlayLayer)
  {
    this._plLayer = paramPlayLayer;
  }

  public void showHelp()
  {
    this._spr1.setVisible(true);
    this._spr2.setVisible(true);
    this._spr3.setVisible(true);
    //this._spr4.setVisible(true);
    setVisible(true);
    CCFadeIn localCCFadeIn = CCFadeIn.action(0.5F);
    CCFiniteTimeAction[] arrayOfCCFiniteTimeAction = new CCFiniteTimeAction[1];
    arrayOfCCFiniteTimeAction[0] = CCCallFuncN.action(this, "spriteMoveFinished");
    runAction(CCSequence.actions(localCCFadeIn, arrayOfCCFiniteTimeAction));
  }

  public void spriteMoveFinished(Object paramObject)
  {
  }
}

/* Location:           C:\Users\Owner\Desktop\test\classes_dex2jar.jar
 * Qualified Name:     com.word.guess.com.HelpLayer
 * JD-Core Version:    0.6.2
 */
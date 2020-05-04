package com.wordguess.com;

import java.util.ArrayList;
import java.util.StringTokenizer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import com.facebook.*;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.google.android.gms.plus.PlusShare;
import com.mobiapplicious.whatstheword.R;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.*;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.*;

// Referenced classes of package com.word.guess.com:
//            MainLayer, PicInfo, PlaceHolderInfo, HelpLayer, 
//            MsgLayer, ThankLayer

public class PlayLayer extends CCColorLayer
{

	public static String _arWord[] = {
		"action_movie","aerial_view","aged","alicia_keys","analog","arc","art","art_deco","auto","baby","baked","bank","bark","bars",
		"bat","beach","berry",
		"bieber",
		"black",
		"blank",
		"blend",
		"blow_up",
		"blue",
		"brew",
		"bright_pink",
		"broken",
		"bunch",
		"candy",
		"card",
		"carry",
		"cats",
		"change",
		"charge",
		"cheers",
		"cheese",
		"choice",
		"circle",
		"citrus",
		"class",
		"clear",
		"clear_cut",
		"climb",
		"coast",
		"coco",
		"code",
		"coffee",
		"cold",
		"colors",
		"country_song",
		"cover",
		"crane",
		"creepy",
		"cross",
		"cube",
		"curl",
		"curve",
		"cute",
		"dance",
		"dark",
		"day_time",
		"device",
		"dinner",
		"dirt",
		"disco",
		"dish",
		"disney",
		"divide",
		"doctor",
		"dog",
		"door",
		"draw",
		"dress_up",
		"drink",
		"drone",
		"early_meal",
		"eatery",
		"edge",
		"edible",
		"empty",
		"exact",
		"exotic",
		"eye",
		"face",
		"faith",
		"fall",
		"fast",
		"fast_food",
		"flags",
		"flame",
		"flash",
		"flat",
		"flavor",
		"float",
		"flower",
		"fluffy",
		"fly",
		"folds",
		"forest",
		"fresh",
		"frozen",
		"fruits",
		"funky",
		"funny",
		"game",
		"gold",
		"grain",
		"green",
		"grow",
		"hang",
		"happy",
		"hard",
		"hip_hop",
		"home",
		"horn",
		"horror_movie",
		"hot",
		"huge",
		"images",
		"ink",
		"jam",
		"join",
		"juicy",
		"jump",
		"junk_food",
		"katy_perry",
		"keys",
		"kick",
		"kiss",
		"latin_music",
		"law",
		"lawn",
		"lean",
		"lick",
		"lil_wayne",
		"loud",
		"lucky",
		"mammal",
		"mariah",
		"mary_blige",
		"metal",
		"milk",
		"mixer",
		"money",
		"mood",
		"music",
		"nail",
		"name",
		"narrow",
		"nba",
		"neon",
		"newest",
		"news_paper",
		"nicki_minaj",
		"night",
		"numbers",
		"obama",
		"ocean",
		"old",
		"old_school",
		"open",
		"outlet",
		"paint",
		"party",
		"pastel",
		"peek",
		"pets",
		"pile",
		"plane",
		"pocket",
		"point",
		"police",
		"pool",
		"print",
		"prism",
		"pull",
		"purple",
		"push",
		"rain",
		"rap_music",
		"rear_view",
		"red",
		"reggae",
		"relax",
		"report",
		"robot",
		"robyn",
		"rock",
		"rock_music",
		"round",
		"running",
		"rust",
		"scale",
		"school",
		"search",
		"sexy",
		"shadow",
		"sharp",
		"shine",
		"shoes",
		"shop",
		"shot",
		"shows",
		"sign",
		"singer",
		"sink",
		"skate",
		"sky",
		"smart_phone",
		"smile",
		"smoke",
		"social_media",
		"soft",
		"solar_system",
		"soup",
		"sour",
		"spices",
		"spicy",
		"spiral",
		"splash",
		"sports",
		"sports_car",
		"stack",
		"storm",
		"strip",
		"summer",
		"sunset",
		"sweet",
		"swim",
		"tablet",
		"tall",
		"taylor_swift",
		"think",
		"toons",
		"tracks",
		"trunk",
		"twist",
		"under_water",
		"united",
		"up_close",
		"wall",
		"war",
		"war_movie",
		"water",
		"wave",
		"weird",
		"wide",
		"window",
		"work",
		"yellow",
		"zoom"
	};
		
	//public static boolean sound=true;
	private static float _spPic = 10F;
	private static String _strAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static float _sxBtnHelp = 0.8F;
	private static float _sxCh = 0.8F;
	private static int _tgCha[] = {
		301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 
		311, 312
	};
	private static int _tgHelp = 401;
	private static int _tgPic[] = {
		101, 102, 103, 104
	};
	private static int _tgPlh[] = {
		201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 
		211, 212
	};
	private int _arWordOrder[];
	private CCSprite _bigPicBack;
	private HelpLayer _hlp;
	private int _iGameStatus;
	private int _iWordGuessStatus;
	private int _indexToGuessWord;
	private int indexToOrder=0;
	private int _indexToResChar;
	private CCLabel _lbCoin;
	private CCLabel _lbWdNum;
	private MsgLayer _msg;
	private int _nCoins;
	private int _nTotalWords;
	private ThankLayer _thank;
	CGSize _winSize;
	private Session session;
	CCSprite topBack;

	protected PlayLayer(ccColor4B cccolor4b)
	{
		super(cccolor4b);
		_nTotalWords = 0;

		

		initSound();
		_winSize = CCDirector.sharedDirector().displaySize();
		System.out.println("Window size: "+_winSize);
		drawBack();
		System.out.println("Image drawn");
		initGame();
		System.out.println("Game Initialized");
		//this.indexToOrder=0;
		//this.decideWordOrder();
		initMsg();
		System.out.println("Message Initialized");
		initHelp();
		System.out.println("Help initialized");
		initThank();
		System.out.println("Thank you initialized");
		setIsTouchEnabled(true);
		System.out.println("Touch enabled");
		this.initSound();
		System.out.println("Sound enabled");
		MainLayer.iScreen = 2;
	}

	private void checkWordGuess()
	{
		System.out.println("Inside checkWordGuess");
		System.out.println("If guess right: "+guessedRight());
		if(guessedRight())
		{
			setCoin(10 + _nCoins);
			System.out.println("Coins: "+ _nCoins);
			System.out.println("Total Words: "+_nTotalWords);
			System.out.println("index to guess word: "+_indexToGuessWord);
			System.out.println("Game status: "+_iGameStatus+" Word guess status: "+_iWordGuessStatus);
			if(_nTotalWords == 1 + indexToOrder)
			//if(indexToOrder==19)
			{
				_iGameStatus = 1;
				_iWordGuessStatus=1;
			}
			showMsg("right");
			if (MainActivity.sound==true) {
				SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), R.raw.correct,false);
			}
			System.out.println("Right");

			return;
		} 
		else
		{
			showMsg("wrong");
			if (MainActivity.sound==true) {
				SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), R.raw.wrong,false);
			}
			//  _iWordGuessStatus=1;
			System.out.println("Wrong");
			return;
		}
	}

	private void clearPlayScreen()
	{
		System.out.println("Inside clearPlayScreen");
		if (this.getChildByTag(_tgPic[0]) != null &&  this.getChildByTag(_tgPlh[0])!=null && this.getChildByTag(_tgCha[0])!=null) {
			for (int i = 0; i < _tgPic.length; i++) {
				removeChildByTag(_tgPic[i], true);
			}
			for (int j = 0; j < _tgPlh.length; j++) {
				removeChildByTag(_tgPlh[j], true);
			}
			for (int k = 0; k < _tgCha.length; k++) {
				removeChildByTag(_tgCha[k], true);
			}
			removeChildByTag(_tgHelp, true);
		}
		System.out.println("Outside clearPlayScreen");
		return;

	}

	private void decideWordOrder()
	{
		int j;
		if(_arWordOrder == null)
		{
			_arWordOrder = new int[this._nTotalWords];
		} else
		{
			int i = 0;
			while(i < _arWordOrder.length) 
			{
				_arWordOrder[i]=-1;
				i++;
			}
		}
		j = 0;
		do
		{
			if(j >= _arWord.length)
			{
				return;
			}
			_arWordOrder[j]=getMyRand(_arWordOrder, _arWord.length);
			System.out.println("Word order: "+_arWordOrder[j]);
			j++;
		} while(true);
	}

	private void displayPicture(boolean flag, int i)
	{
		System.out.println("Inside displayPicture");
		System.out.println("Flag: "+flag+" i: "+i);
		int k;
		if(i != -1)
		{
			int j = 0;
			while(j < _tgPic.length) 
			{
				if(_tgPic[j] != i)
				{
					((CCSprite)getChildByTag(_tgPic[j])).setVisible(flag);
				}
				j++;
			}
			System.out.println("Outside displayPicture k: "+j);
			return;
		}

	}

	private void draw4Pics_small(String s)
	{
		System.out.println("Inside draw4Pics");
		
		String s1 = (new StringBuilder("words/")).append(s).append("/").toString();
		float f = _winSize.height - topBack.getContentSize().height * topBack.getScaleY();
		float f1 = 0.1F * _winSize.width;
		CCSprite ccsprite = CCSprite.sprite((new StringBuilder(String.valueOf(s1))).append("1.png").toString());
		float f2 = (_winSize.width - 2.0F * f1 - _spPic) / 2.0F;
		float f3 = f2 / ccsprite.getContentSize().getWidth();
		float f4 = 0.05F * (f3 * ccsprite.getContentSize().width);
		CGPoint cgpoint = CGPoint.ccp(0.0F, 0.0F);
		int i = 0;
		do
		{
			if(i >= 4)
			{
				System.out.println("Outside draw4Pics");
				return;
			}
			if(i > 0)
			{
				ccsprite = CCSprite.sprite((new StringBuilder(String.valueOf(s1))).append(String.valueOf(i + 1)).append(".png").toString());
			}
			CCSprite ccsprite1 = CCSprite.sprite("pic_back.png");
			ccsprite1.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
			ccsprite1.setPosition(CGPoint.ccp(f1 + (f2 + _spPic) * (float)(i % 2), f - 20F - (f2 + _spPic) * (float)(i / 2)));
			ccsprite1.setScale(f3);
			addChild(ccsprite1);
			ccsprite.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
			ccsprite.setPosition(CGPoint.ccp(f4 + (f1 + (f2 + _spPic) * (float)(i % 2)), f - 20F - (f2 + _spPic) * (float)(i / 2) - f4));
			ccsprite.setScale(0.9F * f3);
			ccsprite.setUserData(new PicInfo(false, ccsprite.getAnchorPoint(), ccsprite.getPosition(), 0.9F * f3));
			addChild(ccsprite, 100, _tgPic[i]);
			if(i == 0)
			{
				cgpoint = ccsprite1.getPosition();
				_bigPicBack = CCSprite.sprite("pic_back.png");
				_bigPicBack.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
				_bigPicBack.setPosition(cgpoint);
			}
			if(i == 3)
			{
				f3 = ((ccsprite1.getPosition().x + ccsprite1.getContentSize().width * ccsprite1.getScale()) - cgpoint.x) / _bigPicBack.getContentSize().width;
				_bigPicBack.setScale(f3);
				addChild(_bigPicBack);
				_bigPicBack.setVisible(false);
			}
			i++;
		} while(true);
	}
	
	private void draw4Pics(String s)
	{
		System.out.println("Inside draw4Pics");
		String s1 = (new StringBuilder("words/")).append(s).append("/").toString();
		float f = _winSize.height - topBack.getContentSize().height * topBack.getScaleY()-20f;//minus 20f
		float f1 = 0.1F * _winSize.width*1.55f;
		CCSprite ccsprite = CCSprite.sprite((new StringBuilder(String.valueOf(s1))).append("1.png").toString());
		float f2 = (_winSize.width - 2.0F * f1 - _spPic) / 2.0F;
		float f3 = f2 / ccsprite.getContentSize().getWidth();
		float f4 = 0.05F * (f3 * ccsprite.getContentSize().width);
		CGPoint cgpoint = CGPoint.ccp(0.0F, 0.0F);
		int i = 0;
		do
		{
			if(i >= 4)
			{
				System.out.println("Outside draw4Pics");
				return;
			}
			if(i > 0)
			{
				ccsprite = CCSprite.sprite((new StringBuilder(String.valueOf(s1))).append(String.valueOf(i + 1)).append(".png").toString());
			}
			CCSprite ccsprite1 = CCSprite.sprite("pic_back.png");
			ccsprite1.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
			ccsprite1.setPosition(CGPoint.ccp(f1 + (f2 + _spPic) * (float)(i % 2)*.9f, f  - (f2 + _spPic) * (float)(i / 2)*.9f));//made 20 to 0 mult .9f
			ccsprite1.setScale(f3*0.9f);//multiplied  .9
			addChild(ccsprite1);
			ccsprite.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
			ccsprite.setPosition(CGPoint.ccp(f4 + (f1 + (f2 + _spPic) * (float)(i % 2)*.9f), f  - (f2 + _spPic) * (float)(i / 2)*.9f - f4));//mult .9f made 20 to 0
			ccsprite.setScale(0.9F * f3*0.9F);//mult .9
			ccsprite.setUserData(new PicInfo(false, ccsprite.getAnchorPoint(), ccsprite.getPosition(), 0.9F * f3*.9f));//mult .9
			addChild(ccsprite, 100, _tgPic[i]);
			if(i == 0)
			{
				cgpoint = ccsprite1.getPosition();
				_bigPicBack = CCSprite.sprite("pic_back.png");
				_bigPicBack.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
				_bigPicBack.setPosition(cgpoint);
			}
			if(i == 3)
			{
				f3 = ((ccsprite1.getPosition().x + ccsprite1.getContentSize().width * ccsprite1.getScale()) - cgpoint.x) / _bigPicBack.getContentSize().width;
				_bigPicBack.setScale(f3);
				addChild(_bigPicBack);
				_bigPicBack.setVisible(false);
			}
			i++;
		} while(true);


	}

	private void drawBack()
	{
		System.out.println("Inside drawback");
		_winSize = CCDirector.sharedDirector().displaySize();
		CCSprite ccsprite = CCSprite.sprite("play_background.png");
		float f = _winSize.width / ccsprite.getContentSize().width;
		float f1 = _winSize.height / ccsprite.getContentSize().height;
		ccsprite.setAnchorPoint(CGPoint.ccp(0.0F, 0.0F));
		ccsprite.setPosition(CGPoint.ccp(0.0F, 0.0F));
		ccsprite.setScaleX(f);
		ccsprite.setScaleY(f1);
		addChild(ccsprite);
		topBack = CCSprite.sprite("play_top_bar.png");
		float f2 = _winSize.width / topBack.getContentSize().width;
		topBack.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
		topBack.setPosition(CGPoint.ccp(0.0F, _winSize.height));
		topBack.setScaleX(f2);
		if(_winSize.width<500 && _winSize.height<900)
		{
			topBack.setScaleY(0.95F);
		}
		
		addChild(topBack);
		float f3 = 0.3F * topBack.getContentSize().height;
		float f4 = topBack.getContentSize().height - 2.0F * f3;
		CCLabel cclabel = CCLabel.makeLabel("PUZZLE:", "ROCB.TTF", f4);
		cclabel.setColor(ccColor3B.ccc3(255, 255, 255));
		cclabel.setAnchorPoint(CGPoint.ccp(0.0F, 0.0F));
		cclabel.setPosition(CGPoint.ccp(20F, f3));
		cclabel.setVisible(true);
		topBack.addChild(cclabel);
		_lbWdNum = CCLabel.makeLabel("0", "ROCB.TTF", f4);
		_lbWdNum.setColor(ccColor3B.ccc3(255, 255, 255));
		_lbWdNum.setAnchorPoint(CGPoint.ccp(0.0F, 0.0F));
		_lbWdNum.setPosition(CGPoint.ccp(20F + cclabel.getContentSize().width, f3));
		_lbWdNum.setVisible(true);
		topBack.addChild(_lbWdNum);
		CCSprite ccsprite1 = CCSprite.sprite("coins.png");
		float f5 = (f2 * topBack.getContentSize().height) / ccsprite1.getContentSize().height;
		ccsprite1.setAnchorPoint(CGPoint.ccp(1.0F, 0.0F));
		ccsprite1.setPosition(CGPoint.ccp(topBack.getContentSize().width, f3 - 15F));
		ccsprite1.setScale(0.5F * f5);
		topBack.addChild(ccsprite1);
		_lbCoin = CCLabel.makeLabel("0", "ROCB.TTF", f4);
		_lbCoin.setColor(ccColor3B.ccc3(255, 255, 255));
		_lbCoin.setAnchorPoint(CGPoint.ccp(1.0F, 0.0F));
		_lbCoin.setPosition(CGPoint.ccp(topBack.getContentSize().width - 0.7F * (f5 * ccsprite1.getContentSize().width), f3));
		_lbCoin.setVisible(true);
		topBack.addChild(_lbCoin);
		System.out.println("Outside drawback");
	}

	private void drawHelpButton()
	{
		System.out.println("Inside drawHelpButton");
		CCSprite ccsprite = (CCSprite)getChildByTag(_tgCha[5]);
		CCSprite ccsprite1 = CCSprite.sprite("HELP_BTN.png");
		float f = ((0.1F + 2.0F * (ccsprite.getContentSize().height * ccsprite.getScaleY())) - ccsprite1.getContentSize().height * _sxBtnHelp) / 2.0F;
		ccsprite1.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
		ccsprite1.setPosition(CGPoint.ccp(0.1F + (ccsprite.getPosition().x + ccsprite.getContentSize().width * ccsprite.getScaleX()), ccsprite.getPosition().y - f));
//		if (this._winSize.height>900 && this._winSize.width>500) {
			ccsprite1.setScaleX(_sxBtnHelp);
			ccsprite1.setScaleY(_sxBtnHelp);
//		}
//		else
//		{
//			ccsprite1.setScaleX(_sxBtnHelp*.5f);
//			ccsprite1.setScaleY(_sxBtnHelp*.5f);
//		}
		addChild(ccsprite1, 100, _tgHelp);
		System.out.println("Outside drawHelpButton");
	}

	private void drawLetters2Pick_small(String s)
	{
		System.out.println("Inside drawLetters2Pick small");
		String s1;
		int ai[];
		int ai1[];
		int i;
		s1 = s.replace("_", "");
		ai = new int[s1.length()];
		ai1 = new int[12];

		for(int ik=0;ik<s1.length();ik++)
		{
			ai[ik] = getMyRand(ai, 12);
			ai1[-1 + ai[ik]] = 1 + _strAlphabets.indexOf(s1.charAt(ik));
			System.out.println("index of letters: "+ ai1[-1 + ai[ik]]+" at index: "+ai[ik]);
		}



		for(int jk=0;jk<12;jk++)
		{
			System.out.println("value of ai1: "+ai1[jk]+" jk= "+jk);
			if(ai1[jk] == 0)
			{
				//ai1[jk] = getMyRand(ai1, _strAlphabets.length());
				int temp=getMyRand(ai1, _strAlphabets.length());
				while(temp==17 || temp==22 || temp==24 || temp==25 || temp==26)
				{
					temp=getMyRand(ai1, _strAlphabets.length());
				}
				ai1[jk]=temp;
			}
			System.out.println("value inputed: "+ai1[jk]);
		}

		float f;
		float f1;
		float f2;
		float f3;
		int k;
		CCSprite ccsprite = (CCSprite)getChildByTag(_tgPlh[-1 + s1.length()]);
		CCSprite ccsprite1 = CCSprite.sprite("HELP_BTN.png");
		CCSprite ccsprite2 = CCSprite.sprite("PLACEHOLDER_BTN.png");
		f = ccsprite.getPosition().y - ccsprite.getContentSize().height * ccsprite.getScaleY();
		f1 = _sxCh;
		f2 = ccsprite2.getContentSize().width;
		f3 = (_winSize.width - 6F * (f1 * ccsprite2.getContentSize().width) - 6F * 0.1F - ccsprite1.getContentSize().width * _sxBtnHelp) / 2.0F;//mult .4f

		for(k=0; k<12;k++)
		{
			CCSprite ccsprite3 = CCSprite.sprite((new StringBuilder(String.valueOf(String.valueOf(_strAlphabets.charAt(-1 + ai1[k]))))).append(".png").toString());
			ccsprite3.setAnchorPoint(0.0F, 1.0F);
			ccsprite3.setPosition(CGPoint.ccp(f3 + (0.1F + f2 * f1) * (float)(k % 6), f - 10F - (0.1F + f2 * f1) * (float)(k / 6)));//mult  .6 .5
			ccsprite3.setScaleX(f1);//mult .5f
			ccsprite3.setScaleY(f1);//mult .5f
			ccsprite3.setUserData(Integer.valueOf(-1 + ai1[k]));
			addChild(ccsprite3, 100, _tgCha[k]);
		}
		System.out.println("Outside drawLetter2Pick small");
		return;
		
	}
	
	private void drawLetters2Pick(String s)
	{
		System.out.println("Inside drawLetters2Pick");
		String s1;
		int ai[];
		int ai1[];
		int i;
		s1 = s.replace("_", "");
		ai = new int[s1.length()];
		ai1 = new int[12];

		for(int ik=0;ik<s1.length();ik++)
		{
			ai[ik] = getMyRand(ai, 12);
			ai1[-1 + ai[ik]] = 1 + _strAlphabets.indexOf(s1.charAt(ik));
			System.out.println("index of letters: "+ ai1[-1 + ai[ik]]+" at index: "+ai[ik]);
		}



		for(int jk=0;jk<12;jk++)
		{
			System.out.println("value of ai1: "+ai1[jk]+" jk= "+jk);
			if(ai1[jk] == 0)
			{
				//ai1[jk] = getMyRand(ai1, _strAlphabets.length());
				int temp=getMyRand(ai1, _strAlphabets.length());
				while(temp==17 || temp==22 || temp==24 || temp==25 || temp==26)
				{
					temp=getMyRand(ai1, _strAlphabets.length());
				}
				ai1[jk]=temp;
			}
			System.out.println("value inputed: "+ai1[jk]);
		}

		float f;
		float f1;
		float f2;
		float f3;
		int k;
		CCSprite ccsprite = (CCSprite)getChildByTag(_tgPlh[-1 + s1.length()]);
		CCSprite ccsprite1 = CCSprite.sprite("HELP_BTN.png");
		CCSprite ccsprite2 = CCSprite.sprite("PLACEHOLDER_BTN.png");
		f = ccsprite.getPosition().y - ccsprite.getContentSize().height * ccsprite.getScaleY();
		f1 = _sxCh;
		f2 = ccsprite2.getContentSize().width;
		f3 = (_winSize.width - 6F * (f1 * ccsprite2.getContentSize().width*.6f) - 6F * 0.1F - ccsprite1.getContentSize().width * _sxBtnHelp*.5f) / 2.0F;//mult .6f with width
		//f3=f3*1.25f;
		for(k=0; k<12;k++)
		{
			CCSprite ccsprite3 = CCSprite.sprite((new StringBuilder(String.valueOf(String.valueOf(_strAlphabets.charAt(-1 + ai1[k]))))).append(".png").toString());
			ccsprite3.setAnchorPoint(0.0F, 1.0F);
			ccsprite3.setPosition(CGPoint.ccp(f3 + (0.1F + f2 * f1) * (float)(k % 6)*.5f, f - 10F - (0.1F + f2 * f1) * (float)(k / 6)*.5f));//mult  .5 .5
			ccsprite3.setScaleX(f1*.5f);//mult .5f
			ccsprite3.setScaleY(f1*.5f);//mult .5f
			ccsprite3.setUserData(Integer.valueOf(-1 + ai1[k]));
			addChild(ccsprite3, 100, _tgCha[k]);
		}
		System.out.println("Outside drawLetter2Pick");
		return;
		
	}

	private void drawPlaceHolders_small(String s)
	{
		System.out.println("Inside drawPlaceHolders small");
		CCSprite ccsprite = (CCSprite)getChildByTag(_tgPic[2]);
		CCSprite ccsprite1 = CCSprite.sprite("PLACEHOLDER_BTN.png");
		float f = ccsprite.getPosition().y - ccsprite.getContentSize().height * ccsprite.getScaleY() - 20F;//made 20 to 10
		float f1 = _sxCh;
		if(s.indexOf("_") == -1)
		{
			float af1[] = getPHDim(s, ccsprite1.getContentSize().width, f1, 0.0F, 0.1F, ccsprite.getPosition().x);
			subDrawPH(ccsprite1.getContentSize().width, af1[1], af1[0], 0.1F, f, s.length(), 0);//mult 1.5F
			return;
		}
		String s1 = s.split("_")[0];
		String s2 = s.split("_")[1];
		float[] _tmp = new float[2];
		float af[];
		float f2;
		if(s2.length() > s1.length())
		{
			af = getPHDim(s2, ccsprite1.getContentSize().width, f1, 0.0F, 0.1F, ccsprite.getPosition().x);
		} else
		{
			af = getPHDim(s1, ccsprite1.getContentSize().width, f1, 0.0F, 0.1F, ccsprite.getPosition().x);
		}
		subDrawPH(ccsprite1.getContentSize().width, af[1], af[0], 0.1F, f, s1.length(), 0);//mult 1.5
		f2 = getSDX1(s2, ccsprite1, af[0], 0.1F);
		subDrawPH(ccsprite1.getContentSize().width, f2, af[0], 0.1F, f - 0.1F - ccsprite1.getContentSize().height * af[0], s2.length(), s1.length());//also mult 1.7 & changed.1f to 0.07F
		System.out.println("Inside drawPlaceHolders small");
	}
	private void drawPlaceHolders(String s)
	{
		System.out.println("Inside drawPlaceHolders");
		CCSprite ccsprite = (CCSprite)getChildByTag(_tgPic[2]);
		CCSprite ccsprite1 = CCSprite.sprite("PLACEHOLDER_BTN.png");
		float f = ccsprite.getPosition().y - ccsprite.getContentSize().height * ccsprite.getScaleY() - 20F;//made 20 to 10
		float f1 = _sxCh;
		if(s.indexOf("_") == -1)
		{
			float af1[] = getPHDim(s, ccsprite1.getContentSize().width*.95f, f1, 0.0F, 0.1F, ccsprite.getPosition().x);//mult .95
			//subDrawPH(ccsprite1.getContentSize().width, af1[1]*1.25f, af1[0], 0.1F, f, s.length(), 0);//mult 1.25F 
			subDrawPH(ccsprite1.getContentSize().width, this._winSize.width*.07f, af1[0], 0.1F, f, s.length(), 0);//mult .1f 
			return;
		}
		String s1 = s.split("_")[0];
		String s2 = s.split("_")[1];
		float[] _tmp = new float[2];
		float af[];
		float f2;
		if(s2.length() > s1.length())
		{
			af = getPHDim(s2, ccsprite1.getContentSize().width*.95f, f1, 0.0F, 0.1F, ccsprite.getPosition().x);//mult .95
		} else
		{
			af = getPHDim(s1, ccsprite1.getContentSize().width*.95f, f1, 0.0F, 0.1F, ccsprite.getPosition().x);//mult .95
		}
		//subDrawPH(ccsprite1.getContentSize().width, af[1]*1.25f, af[0], 0.1F, f, s1.length(), 0);//mult 1.25 
		subDrawPH(ccsprite1.getContentSize().width, this._winSize.width*.07f, af[0], 0.1F, f, s1.length(), 0);//mult .1f
		f2 = getSDX1(s2, ccsprite1, af[0], 0.1F);
		//subDrawPH(ccsprite1.getContentSize().width, f2*1.25F, af[0], 0.1F, f  - ccsprite1.getContentSize().height * af[0]*.5f, s2.length(), s1.length());//also mult 1.25 & removed .1f to 0.1f & .5f with height 
		subDrawPH(ccsprite1.getContentSize().width, this._winSize.width*.07f, af[0], 0.1F, f - .2f -ccsprite1.getContentSize().height * _sxCh*.5f, s2.length(), s1.length());//also mult .7 & removed .1f to 0.07F & .5f with height 
		System.out.println("Inside drawPlaceHolders");
	}

	private void drawPlayScreen(int i)
	{
		System.out.println("Inside drawPlayScreen");
		_iWordGuessStatus = 0;
		_indexToGuessWord = i;
		String s = getWordFromIndex(i);
		_lbWdNum.setString(String.valueOf(this.indexToOrder + 1));
		clearPlayScreen();
		if(this._winSize.height>900 && this._winSize.width>500)
		{
			draw4Pics_small(s);
			drawPlaceHolders_small(s);
			drawLetters2Pick_small(s.toUpperCase());
			drawHelpButton();
		}
		else
		{
			draw4Pics(s);
			drawPlaceHolders(s);
			drawLetters2Pick(s.toUpperCase());
			drawHelpButton();
		}
		_indexToResChar = 0;
		System.out.println("Outside drawPlayScreen");
	}

	private String getCurrentWord()
	{
		System.out.println("Word: "+ _arWord[_indexToGuessWord]);
		return _arWord[_indexToGuessWord];
	}

	private int getIndexToGuessLetter()
	{
		System.out.println("Inside getIndexToGuessLetter");
		int i = -1;
		int j = -1 + getCurrentWord().replace("_", "").length();
		System.out.println("j: "+j);
		do
		{
			if(j < 0)
			{
				System.out.println("i: "+i);
				return i;
			}
			if(((CCSprite)getChildByTag(_tgPlh[j])).getUserData() == null)
			{
				i = j;
			}
			j--;
		} while(true);
	}

	private int getMyRand(int ai[], int i)
	{
		int j;
		do
		{
			j = (int)Math.floor(1.0D + Math.random() * (double)i);
		} while(isDuplicated(ai, j));
		return j;
	}

	private String getWordFromIndex(int i)
	{
		return _arWord[i];
	}

	private boolean guessedRight()
	{
		System.out.println("Inside guessRight");
		String s = getCurrentWord().replace("_", "");
		System.out.println("Current word: "+s);
		StringBuffer stringbuffer = new StringBuffer();
		int i = 0;
		do
		{
			if(i >= s.length())
			{
				System.out.println("i: "+i+" s: "+s.length()+" result: "+stringbuffer.toString().toLowerCase().equals(s));
				return stringbuffer.toString().toLowerCase().equals(s);
			}
			PlaceHolderInfo placeholderinfo = (PlaceHolderInfo)((CCSprite)getChildByTag(_tgPlh[i])).getUserData();
			if(placeholderinfo == null)
			{
				return false;
			}
			stringbuffer.append(_strAlphabets.charAt(placeholderinfo.getiAlpha()));
			i++;
		} while(true);
	}

	private void initGame()
	{
		System.out.println("Inside initGame");
		initGameValues();
		drawPlayScreen(_indexToGuessWord);
		System.out.println("Outside initgame");
	}

	private void initGameValues()
	{
		System.out.println("Inside initgamevalues");
		_nTotalWords = _arWord.length;
		System.out.println("Total words: "+_arWord.length);
		_iGameStatus = 0;
		SharedPreferences sharedpreferences = CCDirector.sharedDirector().getActivity().getSharedPreferences("mypref", 0);
		
		_indexToGuessWord = sharedpreferences.getInt("puzz_num", 0);
		indexToOrder=sharedpreferences.getInt("order", 0);
		_arWordOrder = new int[this._nTotalWords];
		
		String savedOrder=sharedpreferences.getString("word_order","");
		
		if (!savedOrder.equals("")) {
			StringTokenizer st = new StringTokenizer(savedOrder, ",");
			for (int i = 0; i < this._arWordOrder.length; i++) {
				this._arWordOrder[i] = Integer.parseInt(st.nextToken());
			}
			
		}
		else
		{
			this.decideWordOrder();
			_indexToGuessWord=this._arWordOrder[this.indexToOrder]-1;
		}
		//		_indexToGuessWord=0;
//		this.indexToOrder=0;
		setCoin(sharedpreferences.getInt("coin", 0));
		System.out.println("Outside initgamevalues");
	}

	private void initHelp()
	{
		System.out.println("Inside initHelp");
		_hlp = new HelpLayer(ccColor4B.ccc4(0, 0, 0, 255));
		_hlp.setContentSize(CGSize.make(_winSize.width, _winSize.height));
		_hlp.setPosition(CGPoint.ccp(-_winSize.width, 0.0F));
		_hlp.setParentLayer(this);
		addChild(_hlp, 600);
		_hlp.setVisible(false);
		System.out.println("Outside initHelp");
	}

	private void initMsg()
	{
		System.out.println("Inside initMsg");
		_msg = new MsgLayer(ccColor4B.ccc4(0, 0, 0, 255));
		_msg.setContentSize(CGSize.make(_winSize.width, _winSize.height));
		_msg.setParentLayer(this);
		addChild(_msg, 600);
		_msg.setVisible(false);
		System.out.println("Outside initMsg");
	}

	private void initSound()
	{
		System.out.println("Inside initSound");
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.s1);
		System.out.println("Outside initSound");
	}

	private void initThank()
	{
		System.out.println("Inside initThank");
		_thank = new ThankLayer(ccColor4B.ccc4(0, 0, 0, 255));
		_thank.setContentSize(CGSize.make(_winSize.width, _winSize.height));
		_thank.setPosition(CGPoint.ccp(-_winSize.width, 0.0F));
		addChild(_thank, 600);
		_thank.setVisible(false);
		System.out.println("Outside initThank");
	}

	private boolean isDuplicated(int ai[], int i)
	{
		int j = 0;
		do
		{
			if(j >= ai.length)
			{
				return false;
			}
			if(ai[j] == i)
			{
				return true;
			}
			j++;
		} while(true);
	}

	private boolean onClickAlpha(int i)
	{
		System.out.println("Inside onCLickAlpha");
		System.out.println("i: "+i);
		System.out.println("Word guess: "+_iWordGuessStatus);
		System.out.println("Game status: "+_iGameStatus);
		System.out.println("Index to guess letter: "+getIndexToGuessLetter());
		while(_iWordGuessStatus == 1 || _iGameStatus == 1 || getIndexToGuessLetter() == -1) 
		{
			System.out.println("Inside while");
			return false;
		}
		if (MainActivity.sound==true) {
			SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), 0x7f040000,false);
		}
		setGuessedChar(i, ((Integer)((CCSprite)getChildByTag(i)).getUserData()).intValue());
		System.out.println("Outside onClickAlpha");
		return true;
	}

	private boolean onClickHelpButton()
	{
		System.out.println("Inside onClickHelpButton");
		_hlp.setPosition(CGPoint.ccp(0.0F, 0.0F));
		_hlp.showHelp();
		System.out.println("Help shown and outside onClickHelpButton");
		return true;
	}

	private boolean onClickPicture(int i)
	{
		System.out.println("Inside onClickPicture");
		CCSprite ccsprite = (CCSprite)getChildByTag(_tgPic[0]);
		CCSprite ccsprite1 = (CCSprite)getChildByTag(_tgPic[3]);
		CCSprite ccsprite2 = (CCSprite)getChildByTag(i);
		PicInfo picinfo = (PicInfo)ccsprite2.getUserData();
		if(!picinfo.isZoomed())
		{
			_bigPicBack.setVisible(true);
			picinfo.setZoomed(true);
			ccsprite2.setUserData(picinfo);
			float f = ((ccsprite1.getPosition().x + ccsprite1.getContentSize().width * ccsprite1.getScale()) - ccsprite.getPosition().x) / ccsprite2.getContentSize().width;
			ccsprite2.setPosition(ccsprite.getPosition());
			if(_winSize.width<500 && _winSize.height<900)
			{
				ccsprite2.setScale(_bigPicBack.getScale()*.95f);
			}
			else
			{
				ccsprite2.setScale(_bigPicBack.getScale()*.95f);
			}
			CCFadeIn ccfadein = CCFadeIn.action(0.5F);
			CCFiniteTimeAction accfinitetimeaction[] = new CCFiniteTimeAction[1];
			accfinitetimeaction[0] = CCCallFuncN.action(this, "spriteMoveFinished");
			ccsprite2.runAction(CCSequence.actions(ccfadein, accfinitetimeaction));
			displayPicture(false, i);
			System.out.println("Outside onClickPicture");
			return true;
		} else
		{
			_bigPicBack.setVisible(false);
			picinfo.setZoomed(false);
			ccsprite2.setUserData(picinfo);
			ccsprite2.setAnchorPoint(picinfo.getAnchor());
			ccsprite2.setPosition(picinfo.getPos());
			ccsprite2.setScale(picinfo.getScale());
			displayPicture(true, i);
			System.out.println("Outside onClickPicture");
			return true;
		}

	}

	private boolean onClickPlaceHolder(int i)
	{
		System.out.println("Inside onClickPlaceholder");
		if (MainActivity.sound) {
			SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), 0x7f040000,false);
		}
		CCSprite ccsprite = (CCSprite)getChildByTag(i);
		PlaceHolderInfo placeholderinfo;
		if(ccsprite.getUserData() != null)
		{
			if((placeholderinfo = (PlaceHolderInfo)ccsprite.getUserData()).getiTag() != -1)
			{
				((CCSprite)getChildByTag(placeholderinfo.getiTag())).setVisible(true);
				CCSprite ccsprite1 = CCSprite.sprite("PLACEHOLDER_BTN.png");
				ccsprite1.setAnchorPoint(ccsprite.getAnchorPoint());
				ccsprite1.setPosition(ccsprite.getPosition());
				ccsprite1.setScale(ccsprite.getScale());
				removeChildByTag(i, true);
				addChild(ccsprite1, 100, i);
				System.out.println("Outside onClickPlaceholder");
				return true;
			}
		}
		System.out.println("Outside onClickPlaceholder");
		return false;
	}

	private void publishFeedDialog()
	{
		System.out.println("Inside publishedFeedDialog");
		Bundle bundle = new Bundle();
		bundle.putString("name", "Word Guess");
		bundle.putString("caption", "Share the guess with your friend!");

		WebDialog feedDialog = (
				new WebDialog.FeedDialogBuilder(CCDirector.sharedDirector().getActivity(),
						Session.getActiveSession(),
						bundle)).setOnCompleteListener(new OnCompleteListener() {

							@Override
							public void onComplete(Bundle values,
									FacebookException error) {
								if (error == null) {
									// When the story is posted, echo the success
									// and the post Id.
									final String postId = values.getString("post_id");
									if (postId != null) {
										Toast.makeText(CCDirector.sharedDirector().getActivity(),
												"Posted story, id: "+postId,
												Toast.LENGTH_SHORT).show();
									} else {
										// User clicked the Cancel button
										Toast.makeText(CCDirector.sharedDirector().getActivity().getApplicationContext(), 
												"Publish cancelled", 
												Toast.LENGTH_SHORT).show();
									}
								} else if (error instanceof FacebookOperationCanceledException) {
									// User clicked the "x" button
									Toast.makeText(CCDirector.sharedDirector().getActivity().getApplicationContext(), 
											"Publish cancelled", 
											Toast.LENGTH_SHORT).show();
								} else {
									// Generic, ex: network error
									Toast.makeText(CCDirector.sharedDirector().getActivity().getApplicationContext(), 
											"Error posting story", 
											Toast.LENGTH_SHORT).show();
								}
							}

						})
						.build();
		feedDialog.show();

		//        ((com.facebook.widget.WebDialog.FeedDialogBuilder)(new com.facebook.widget.WebDialog.FeedDialogBuilder(CCDirector.sharedDirector().getActivity(), Session.getActiveSession(), bundle)).setOnCompleteListener(new com.facebook.widget.WebDialog.OnCompleteListener() {
		//
		//            final PlayLayer this$0;
		//
		//            public void onComplete(Bundle bundle1, FacebookException facebookexception)
		//            {
		//            	//super();
		//                if(facebookexception == null)
		//                {
		//                    if(bundle1.getString("post_id") != null)
		//                    {
		//                        Log.i("POST!", "SUCCESS!");
		//                        setCoin(100 + _nCoins);
		//                        storeSetting(_indexToGuessWord, _nCoins,indexToOrder);
		//                    }
		//                    System.out.println("Outside PublishFeedDialog");
		//                    return;
		//                } else
		//                {
		//                    boolean _tmp = facebookexception instanceof FacebookOperationCanceledException;
		//                    System.out.println("Outside PublishFeedDialog");
		//                    System.out.println("Facebook exception: "+facebookexception);
		//                    return;
		//                }
		//            }
		//
		//            
		//            {
		//                this$0 = PlayLayer.this;
		//                
		//            }
		//        })).build().show();
	}

	public static CCScene scene()
	{
		CCScene ccscene = CCScene.node();
		ccscene.addChild(new PlayLayer(ccColor4B.ccc4(0, 0, 0, 255)));
		return ccscene;
	}

	private void setCoin(int i)
	{
		_nCoins = i;
		_lbCoin.setString(String.valueOf(i));
	}

	private void setGuessedChar(int i, int j)
	{
		System.out.println("Inside setGuessedChar");
		System.out.println("i: "+i+"j: "+j);
		int k = getIndexToGuessLetter();
		System.out.println("k: "+k);
		CCSprite ccsprite = (CCSprite)getChildByTag(_tgPlh[k]);
		if(i > 0)
		{
			((CCSprite)getChildByTag(i)).setVisible(false);


		}

		CCSprite ccsprite1 = CCSprite.sprite((new StringBuilder(String.valueOf(_strAlphabets.charAt(j)))).append(".png").toString());
		ccsprite1.setAnchorPoint(ccsprite.getAnchorPoint());
		ccsprite1.setPosition(ccsprite.getPosition());
		ccsprite1.setScale(ccsprite.getScale());
		removeChildByTag(_tgPlh[k], true);
		ccsprite1.setUserData(new PlaceHolderInfo(i, j));
		addChild(ccsprite1, 100, _tgPlh[k]);
		System.out.println("Index to guess letter: "+getIndexToGuessLetter());
		if(getIndexToGuessLetter() == -1)
		{
			checkWordGuess();
		}

	}

	private void storeSetting(int i, int j, int k)
	{
		android.content.SharedPreferences.Editor editor = CCDirector.sharedDirector().getActivity().getSharedPreferences("mypref", 0).edit();
		editor.putInt("order", k);
		editor.putInt("puzz_num", i);
		editor.putInt("coin", j);
		
		StringBuilder str=new StringBuilder();
		for(int m=0;m<this._arWordOrder.length;m++)
		{
			str.append(_arWordOrder[m]).append(",");
		}
		System.out.println("Word order: "+str.toString());
		editor.putString("word_order", str.toString());
		editor.commit();
	}

	public boolean ccTouchesEnded(MotionEvent motionevent)
	{
		System.out.println("Inside ccTouchesEnded");
		System.out.println("message visible: "+_msg.getVisible()+" game status: "+_iGameStatus+" Word guess status: "+_iWordGuessStatus);
		if(!_msg.getVisible() || _iGameStatus != 1 || _iWordGuessStatus != 1)
		{
			System.out.println("Inside ccTouchesEnded and if");
			CGPoint cgpoint;
			int i;
			cgpoint = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(motionevent.getX(), motionevent.getY()));
			
			System.out.println("Touch point X: "+motionevent.getX()+" Touch point Y: "+motionevent.getY());
			i = 0;

			for(;i<_tgPic.length;i++)
			{
				CCSprite ccsprite = (CCSprite)getChildByTag(_tgPic[i]);
				if(ccsprite != null && ccsprite.getVisible() && CGRect.containsPoint(ccsprite.getBoundingBox(), cgpoint))
				{
					System.out.println("Picture touched: "+" tag number: "+_tgPic[i]);
					return onClickPicture(_tgPic[i]);
				}
			}

			int j=0;

			for(;j<_tgPlh.length;j++)
			{
				CCSprite ccsprite1 = (CCSprite)getChildByTag(_tgPlh[j]);
				if(ccsprite1 != null && ccsprite1.getVisible() && CGRect.containsPoint(ccsprite1.getBoundingBox(), cgpoint))
				{
					System.out.println("Placeholder touched: "+" tag number: "+_tgPlh[j]);
					return onClickPlaceHolder(_tgPlh[j]);
				}
			}

			int k=0;

			for(;k<_tgCha.length;k++)
			{
				CCSprite ccsprite2 = (CCSprite)getChildByTag(_tgCha[k]);
				if(ccsprite2 != null && ccsprite2.getVisible() && CGRect.containsPoint(ccsprite2.getBoundingBox(), cgpoint))
				{
					System.out.println("Character touched: " +" tag number: "+_tgCha[k]);
					return onClickAlpha(_tgCha[k]);
				}
			}

			if(CGRect.containsPoint(((CCSprite)getChildByTag(_tgHelp)).getBoundingBox(), cgpoint))
			{
				System.out.println("Help touched: ");
				onClickHelpButton();
				return true;
			}
			else
				return true;
		}
		else
			return true;

	}

	public float[] getPHDim(String s, float width, float f, float f1, float f2, float f3)
	{
		float af[] = {
				f, f1
		};
		float f4;
		if(s.length() <= 4)
		{
			//f4 = (_winSize.width - f * ccsprite.getContentSize().width * (float)s.length() - f2 * (float)(-1 + s.length())) / 2.0F;
			f4 = (_winSize.width - f * width * (float)s.length() - f2 * (float)(-1 + s.length())) / 2.0F;
		} else
		{
			f4 = f3;
			//f = (_winSize.width - 2.0F * f4 - f2 * (float)(-1 + s.length())) / (float)s.length() / ccsprite.getContentSize().width;
			f = (_winSize.width - 2.0F * f4 - f2 * (float)(-1 + s.length())) / (float)s.length() / width;
		}
		af[0] = f;
		af[1] = f4;
		return af;
	}

	public float getSDX1(String s, CCSprite ccsprite, float f, float f1)
	{
		return (_winSize.width - f * ccsprite.getContentSize().width * (float)s.length() - f1 * (float)(-1 + s.length())) / 2.0F;
	}

	public void onContinue()
	{
		System.out.println("Inside onContinue");
		Log.i("CONTINUEE!", "CONTINUE");
		if(_iGameStatus == 1)
		{
			storeSetting(0, 0,0);
			_thank.setPosition(CGPoint.ccp(0.0F, 0.0F));
			_thank.setVisible(true);
			System.out.println("Thanks visible");
			return;
		} else
		{
			System.out.println("Index to order: "+indexToOrder);
			indexToOrder++;
			System.out.println("Word order: "+this._arWordOrder[this.indexToOrder]);
			drawPlayScreen(this._arWordOrder[this.indexToOrder]-1);

			storeSetting(_indexToGuessWord, _nCoins,indexToOrder);
			System.out.println("Continued to next game");
			//onPlayAgain();
			return;
		}
	}

	public void onPlayAgain()
	{

		initGame();
	}

	public void onShareTwitter()
	{
		String message="Share the guess with your friend!";
		_nCoins=_nCoins+100;
		setCoin(_nCoins);
		this.storeSetting(_indexToGuessWord,_nCoins, indexToOrder);
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("https://twitter.com/intent/tweet?text=Hurrah!!! I have just got " + _nCoins + " points in WordGuess(https://play.google.com/store/apps/details?id=com.wordguess.com) for Android !!!!"));
		CCDirector.sharedDirector().getActivity().startActivity(i);
		
	}
	public void onShareGplus()
	{
		_nCoins=_nCoins+100;
		setCoin(_nCoins);
		this.storeSetting(_indexToGuessWord,_nCoins, indexToOrder);
		Intent shareIntent = new PlusShare.Builder(CCDirector.sharedDirector().getActivity())
        .setType("text/plain")
        .setText("Share the guess with your friend!")
        .getIntent();

		CCDirector.sharedDirector().getActivity().startActivityForResult(shareIntent, 0);
		//onContinue();
//		Intent intent = new Intent("android.intent.category.LAUNCHER");
//		intent.setClassName("com.wordguess.com", "com.wordguess.com.MainActivity");
//		CCDirector.sharedDirector().getActivity().startActivity(intent);
		
		//this.initGame();
	}
	public void onShareFB()
	{
		System.out.println("Inside Shared on Fb");
		String message="Hurrah!!! I have just got " + _nCoins + " points in WordGuess(https://play.google.com/store/apps/details?id=com.wordguess.com) for Android !!!!";
		_nCoins=_nCoins+100;
		setCoin(_nCoins);
		this.storeSetting(_indexToGuessWord,_nCoins, indexToOrder);
//		Intent intent = new Intent("android.intent.category.LAUNCHER");
//		intent.setClassName("com.facebook.katana", "com.facebook.katana.LoginActivity");
		String uri = "facebook://facebook.com/intent/post?status="+Uri.encode(message);
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		CCDirector.sharedDirector().getActivity().startActivity(intent);
		
		
//		Bundle bundle = new Bundle();
//		bundle.putString("name", "Word Guess");
//		bundle.putString("caption", "Share the guess with your friend!");
//		
//		String  response = facebook.request("me/feed",parameters,"POST");
		
		//        Session.openActiveSession(CCDirector.sharedDirector().getActivity(), true, new com.facebook.Session.StatusCallback() {
		//
		//            //final PlayLayer this$0;
		//
		//            public void call(Session session1, SessionState sessionstate, Exception exception)
		//            {
		//            	System.out.println("Session open: "+session1.isOpened());
		//                if(session1.isOpened())
		//                {
		//                    Log.i("SESSION OPEND OPEN!", "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
		//                    publishFeedDialog();
		//                }
		//                else
		//                {
		//                	
		//                	Toast.makeText(CCDirector.sharedDirector().getActivity().getApplicationContext(), "Fb not signed in", Toast.LENGTH_SHORT);
		//                }
		//            }
		//            
		////            {
		////                this$0 = PlayLayer.this;
		////                //super();
		////            }
		//        });
	}

	public void onShowOneLetter()
	{
		System.out.println("Inside onShowOneLetter");
		if(_nCoins < 50)
		{
			showMsg("coinlack");
			System.out.println("Lack of coins");
			return;
		} else
		{
			String s = getCurrentWord().replace("_", "");
			System.out.println("Value of: "+String.valueOf(s.charAt(getIndexToGuessLetter())).toUpperCase());
			String temp=String.valueOf(s.charAt(getIndexToGuessLetter())).toUpperCase();
			int j= _strAlphabets.indexOf(String.valueOf(s.charAt(getIndexToGuessLetter())).toUpperCase());
			System.out.println("Value of j: "+j);
			int i=-1;
			for(int k=0;k<_tgCha.length;k++)
			{
				CCSprite child=(CCSprite) getChildByTag(_tgCha[k]);
				int userdata=(Integer) child.getUserData();
				System.out.println("user data: "+userdata);
				if(userdata==j)
				{
					System.out.println("value of tag: "+k);
					i=_tgCha[k];
				}
			}
			
			setGuessedChar(i, _strAlphabets.indexOf(String.valueOf(s.charAt(getIndexToGuessLetter())).toUpperCase()));
			setCoin(-50 + _nCoins);
			System.out.println("One letter shown");
			return;
		}
	}

	public void onTryAgain()
	{
	}

	public void showMsg(String s)
	{
		System.out.println("Inside showMsg");
		System.out.println("Message: "+s);
		_msg.setPosition(CGPoint.ccp(0.0F, 0.0F));
		_msg.showMsg(s);
	}

	public void spriteMoveFinished(Object obj)
	{
	}

	public void subDrawPH(float f, float f1, float f2, float f3, float f4, int i, int j)
	{
		float f5 = f * f2;
		int k = 0;
		if (this._winSize.width<500 && this._winSize.height<900) {
			f1 = f1 + 40f;//added this line
		}
		do
		{
			if(k >= i)
			{
				return;
			}
			
			CCSprite ccsprite = CCSprite.sprite("PLACEHOLDER_BTN.png");
			ccsprite.setAnchorPoint(CGPoint.ccp(0.0F, 1.0F));
			if (this._winSize.width<500 && this._winSize.height<900)
			{
				ccsprite.setScale(_sxCh*.5f);//mult .65
				ccsprite.setPosition(CGPoint.ccp(f1 + (f5 + f3) * (float)k*.85f, f4));//mult .65f
			}
			else
			{
				ccsprite.setScale(f2);
				ccsprite.setPosition(CGPoint.ccp(f1 + (f5 + f3) * (float)k, f4));
			}
			addChild(ccsprite, 100, _tgPlh[k + j]);
			k++;
		} while(true);
	}






}

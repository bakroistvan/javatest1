package hu.bakro.test1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : TiliToli
//  @ File Name : MyFace.java
//  @ Date : 2014.05.04.
//  @ Author : Bakr� Nagy Istv�n
//
//

public class MyFace {
	private BufferedImage _pic;
	private BufferedImage[][] _ims;
	private final int _xNum;
	private final int _yNum;
	private final int _sidePixels;
	
	/**
	 * MyFace konstruktora, ami a path-al definialt kepet megnyitja es xNum,yNum darabra vegja szet.
	 * 
	 * @param    path       kep eleresi cime
	 * @param    xNum		A tabla x iranyu merete
	 * @param    yNum		A tabla y iranyu merete
	 * @param    sidePixels  Feldarabolt elemek szelessege pixelben.
	 * @throws IOException Ha nem tudja olvasni a kepet.
	**/
	public MyFace(String path, int xNum, int yNum, int sidePixels) throws IOException {
		File picFile = new File(path);
		_pic = ImageIO.read(picFile); 
		_ims = new BufferedImage[xNum][yNum];
		_xNum = xNum;
		_yNum = yNum;
		_sidePixels = sidePixels;
		
		
		
		slicing();
	}
	
	/**
	 * Adott elem kep-reszletenek visszaadasa.
	 *
	 * @param    x Elem x indexe
	 * @param    y Elem y indexe
	**/
	public BufferedImage getFace(int x, int y) {
		return _ims[x][y];
	}
	
	/**
	 * A kirakando kep megvaltoztatasa.
	 *
	 * @param    path	Uj kep eleresi utja
	 * @throws IOException Ha nem tudja a kepet megnyitni
	**/
	public void changePic(String path) throws IOException {
		_pic = ImageIO.read(new File(path));
		slicing();
	}
	
	/**
	 * Kep feldarabolasa es a megkapott elem-kepek eltarolasa.
	**/
	public void slicing() {
		int dw = (int)Math.floor(_pic.getWidth() / _yNum);
		int dh = (int)Math.floor(_pic.getHeight() / _xNum);
		
		for(int xx = 0; xx < _xNum; xx++) {
			for(int yy = 0; yy < _yNum; yy++) {
				_ims[xx][yy] = _pic.getSubimage(dw * yy, dh * xx, dw, dh);
			}
		}
	}
}

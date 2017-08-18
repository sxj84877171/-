package com.sxj.carloan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

public class SearchGoogleUtil {

	private static final double OFFSETLAT = -0.00264;
	private static final double OFFSETLON = 0.00545;

	/**
	 * 根据经纬度反向解析地址，有时需要多尝试几次
	 * 注意:(摘自：http://code.google.com/intl/zh-CN/apis/maps/faq.html
	 * 提交的地址解析请求次数是否有限制？) 如果在 24 小时时段内收到来自一个 IP 地址超过 2500 个地址解析请求， 或从一个 IP
	 * 地址提交的地址解析请求速率过快，Google 地图 API 编码器将用 620 状态代码开始响应。 如果地址解析器的使用仍然过多，则从该 IP
	 * 地址对 Google 地图 API 地址解析器的访问可能被永久阻止。
	 * 
	 * @param latitude
	 *            纬度
	 * @param longitude
	 *            经度
	 * @return
	 */
	public static String getAddr(double latitude, double longitude) {
		String addr = "";

		// 也可以是http://maps.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s，不过解析出来的是英文地址
		// 密钥可以随便写一个key=abc
		// output=csv,也可以是xml或json，不过使用csv返回的数据最简洁方便解析
		
		
		//http://api.map.baidu.com/geocoder/v2/?
		//callback=renderReverse&location=39.983424,116.322987&output=json&pois=1&ak=HGo6NAYh98bInCRFCNsGVCYtTDBOedzU
		String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + latitude
				+ "," + longitude + "&output=json&pois=1&ak=HGo6NAYh98bInCRFCNsGVCYtTDBOedzU&ret_coordtype=wgs84&mcode=27:01:BE:26:74:9F:D0:21:E4:0E:CC:12:EC:83:97:97:A0:E1:C9:65;com.soarsky.instrument";
		URL myURL = null;
		HttpURLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		try {
			httpsConn = (HttpURLConnection) myURL.openConnection();
			httpsConn.setRequestMethod("GET");
			httpsConn.setRequestProperty("Accept-Language", "zh-CN");
			httpsConn.setReadTimeout(10000);
			httpsConn.setConnectTimeout(10000);
			httpsConn.connect();
			int responseCode = httpsConn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return "网络设置问题";
			}
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(
						httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
//					System.out.println(data);
					if(data != null){
						return extraStr(data);
					}
				}
				insr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "超时";
		} finally {
			httpsConn.disconnect();
		}
		return addr;
	}
	
	
	public static String extraStr(String str){
		String ret = "" ;
		if(str.contains("formatted_address")){
			int index = str.indexOf("formatted_address");
			StringBuilder sb = new StringBuilder();
			boolean start = false;
			for(int i =index + "formatted_address".length() +1 ; i < str.length() ; i++){
				
				if(str.charAt(i) == '"'){
					if(start == true){
						break;
					}else{
						start = true;
					}
				}else if(start){
					sb.append(str.charAt(i));
				}
				
			}
			ret = sb.toString();
		}
		
		return ret;
	}

	/**
	 * 采用Google Android的api获取信息
	 * 
	 * @param currentLocation
	 * @param mContext
	 * @return
	 */
	public static String getAddr(Location currentLocation, Context mContext) {
		// 解析地址并显示
		String addressStr = "";
		Geocoder geoCoder = new Geocoder(mContext);
		try {
			double latitude = currentLocation.getLatitude() + OFFSETLAT;
			double longitude = currentLocation.getLongitude() + OFFSETLON;
			List<Address> list = geoCoder.getFromLocation(latitude, longitude,
					1);
			for (int i = 0; i < list.size(); i++) {
				Address address = list.get(i);
				// addressStr = address.getCountryName() +
				// address.getAdminArea()
				// + address.getFeatureName();
				addressStr = address.getAddressLine(0)
						+ address.getAddressLine(1) + address.getAddressLine(2)
						+ " "+address.getAddressLine(3);
			}
		} catch (IOException e) {
			return e.getMessage();
		}
		return addressStr;
	}
}

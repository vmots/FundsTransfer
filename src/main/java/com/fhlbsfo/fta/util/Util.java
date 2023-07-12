package com.fhlbsfo.fta.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
	
public	double formatTwoDecimal(double d) {
		
		BigDecimal bd = BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
		}


}
 
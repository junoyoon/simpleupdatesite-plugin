/*
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY. Use is subject to license terms.
 */
package hudson.plugins.simpleupdatesite.util;

import java.lang.Character.UnicodeBlock;

/**
 * Utility Class
 * 
 * @author JunHo Yoon
 */
public class HudsonUtil {
	public int length(String str) {
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
			if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) || UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
				|| UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)) {
				length += 2;
			} else {
				length++;
			}
		}
		return length;
	}
}

package net.wfoas.woma.slgh.ncr.i;

public final class CRElement {
	String s1 = null, s2 = null, s3 = null, s4 = null, s5sf = null, s6 = null, s7 = null, s8 = null, s9 = null,
			sr = null;
	boolean furnace;
	boolean shapeless;
	final String author;
	String name;
	String notes;

	// Furnace-INPUT-String: true;sf;sr;agasdgargafgsdghstharg
	// Crafting-ShapedInputString:
	// false;s1;s2;s3;s4;s5;s6;s7;s8;s9;sr;false;srfgsfregaergsdfgaerg
	public CRElement(String fromString, String author, String name) {
		this.author = author;
		if (fromString == null)
			return;
		if (fromString.equalsIgnoreCase(""))
			return;
		String[] elements = fromString.split(";");
		furnace = Boolean.parseBoolean(elements[0]);
		if (furnace) {
			s5sf = setIfNull(elements[1]);
			sr = setIfNull(elements[2]);
			for (int index = 3; index < elements.length; index++) {
				if (notes == null) {
					notes = setIfNull(elements[index]);
				} else {
					if (!"null".equalsIgnoreCase(elements[index]))
						notes = notes + elements[index];
				}
			}
			return;
		} else {
			s1 = setIfNull(elements[1]);
			s2 = setIfNull(elements[2]);
			s3 = setIfNull(elements[3]);
			s4 = setIfNull(elements[4]);
			s5sf = setIfNull(elements[5]);
			s6 = setIfNull(elements[6]);
			s7 = setIfNull(elements[7]);
			s8 = setIfNull(elements[8]);
			s9 = setIfNull(elements[9]);
			sr = setIfNull(elements[10]);
			shapeless = Boolean.parseBoolean(elements[11]);
			for (int index = 12; index < elements.length; index++) {
				if (notes == null) {
					notes = elements[index];
				} else {
					notes = notes + elements[index];
				}
			}
		}
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getS3() {
		return s3;
	}

	public void setS3(String s3) {
		this.s3 = s3;
	}

	public String getS4() {
		return s4;
	}

	public void setS4(String s4) {
		this.s4 = s4;
	}

	public String getS5sf() {
		return s5sf;
	}

	public void setS5sf(String s5sf) {
		this.s5sf = s5sf;
	}

	public String getS6() {
		return s6;
	}

	public void setS6(String s6) {
		this.s6 = s6;
	}

	public String getS7() {
		return s7;
	}

	public void setS7(String s7) {
		this.s7 = s7;
	}

	public String getS8() {
		return s8;
	}

	public void setS8(String s8) {
		this.s8 = s8;
	}

	public String getS9() {
		return s9;
	}

	public void setS9(String s9) {
		this.s9 = s9;
	}

	public String getSr() {
		return sr;
	}

	public void setSr(String sr) {
		this.sr = sr;
	}

	public boolean isFurnace() {
		return furnace;
	}

	public void setFurnace(boolean furnace) {
		this.furnace = furnace;
	}

	public boolean isShapeless() {
		return shapeless;
	}

	public void setShapeless(boolean shapeless) {
		this.shapeless = shapeless;
	}

	public String getAuthor() {
		return author;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public CRElement(String author, String name) {
		this(null, author, name);
	}

	public static final char SEPERATOR = ';';

	public String serialize() {
		if (furnace)
			return furnace + "" + SEPERATOR + nonNull(s5sf) + SEPERATOR + nonNull(sr) + SEPERATOR + nonNull(notes);
		return furnace + "" + SEPERATOR + nonNull(s1) + SEPERATOR + nonNull(s2) + SEPERATOR + nonNull(s3) + SEPERATOR
				+ nonNull(s4) + SEPERATOR + nonNull(s5sf) + SEPERATOR + nonNull(s6) + SEPERATOR + nonNull(s7)
				+ SEPERATOR + nonNull(s8) + SEPERATOR + nonNull(s9) + SEPERATOR + nonNull(sr) + SEPERATOR + shapeless
				+ SEPERATOR + nonNull(notes);
	}

	public static String nonNull(String s) {
		return s == null ? "null" : (s.equalsIgnoreCase("") ? "null" : s);
	}

	public static String setIfNull(String s) {
		return s.equalsIgnoreCase("null") ? null : s;
	}

	public String toString() {
		return serialize();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
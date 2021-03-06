package org.obfuscate4e;

/**
 * This class represents an Obfuscate4e exclusion statement.<br>
 * This class has no visible constructor, but some factory methods.
 * 
 * @author fwo
 * 
 */
public class ObfuscationExclusion {

	public static final int CLASS = 0;
	public static final int INTERFACE = 1;
	public static final int PACKAGE = 2;
	public static final int[] ALL_TYPES = { CLASS, PACKAGE, INTERFACE };

	public static final int NO_RELATION = 10;
	public static final int EXTENDS = 11;
	public static final int IMPLEMENTS = 12;

	private int itsType;
	private int itsRelation;

	private boolean itsFlagGenerated;
	private boolean itsFlagRelating;

	private String itsPath;

	private String itsDescription;

	/**
	 * factory method for a simple class exclusion. all public and protected
	 * fields and methods will get excluded from the obfuscation.
	 * 
	 * @param path
	 *            fully qualified path of the class to exclude
	 * @param comment
	 *            the description, how this exclusion was instantiated
	 * @return the class exclusion
	 * @throws GeneralObfuscate4eException
	 */
	public static ObfuscationExclusion createSimpleClassExclusion(String path,
			String comment) throws GeneralObfuscate4eException {
		ObfuscationExclusion exclusion = new ObfuscationExclusion(CLASS, path,
				comment);
		exclusion.itsRelation = NO_RELATION;
		exclusion.itsFlagRelating = false;
		return exclusion;
	}

	/**
	 * factory method for a class inheritance exclusion. all public fields and
	 * methods for all classes that inherit from the given class will get
	 * excluded from the obfuscation
	 * 
	 * @param path
	 *            fully qualified path of the inherited class
	 * @param comment
	 *            the description, how this exclusion was instantiated
	 * @return the class inheritance exclusion
	 * @throws GeneralObfuscate4eException
	 */
	public static ObfuscationExclusion createClassInheritanceExclusion(
			String path, String comment) throws GeneralObfuscate4eException {
		ObfuscationExclusion exclusion = new ObfuscationExclusion(CLASS, path,
				comment);
		exclusion.itsFlagRelating = true;
		exclusion.itsRelation = EXTENDS;
		return exclusion;
	}

	/**
	 * factory method for an interface implementation exclusion. all public
	 * fields and methods for all classes that implement the given interface
	 * will get excluded from the obfuscation.
	 * 
	 * @param path
	 *            fully qualified path of the implemented interface
	 * @param comment
	 *            the description, how this exclusion was instantiated
	 * @return the interface implementation exclusion
	 * @throws GeneralObfuscate4eException
	 */
	public static ObfuscationExclusion createInterfaceImplementationExclusion(
			String path, String comment) throws GeneralObfuscate4eException {
		ObfuscationExclusion exclusion = new ObfuscationExclusion(INTERFACE,
				path, comment);
		exclusion.itsFlagRelating = true;
		exclusion.itsRelation = IMPLEMENTS;
		return exclusion;
	}

	/**
	 * factory method for a package exclusion. all public and protected fields
	 * and methods for all classes in the given interface will get excluded from
	 * the obfuscation.
	 * 
	 * @param path
	 *            fully qualified path of the package
	 * @param comment
	 *            the description, how this exclusion was instantiated
	 * @return the package exclusion
	 * @throws GeneralObfuscate4eException
	 */
	public static ObfuscationExclusion createPackageExclusion(String path,
			String comment) throws GeneralObfuscate4eException {
		ObfuscationExclusion exclusion = new ObfuscationExclusion(PACKAGE,
				path, comment);
		exclusion.itsRelation = NO_RELATION;
		exclusion.itsFlagRelating = false;
		return exclusion;
	}

	/**
	 * the constructor for the representation of an exclusion from the
	 * obfuscation.
	 * 
	 * @param type
	 * @param qualifier
	 * @param description
	 * @throws GeneralObfuscate4eException
	 */
	private ObfuscationExclusion(int type, String qualifier, String description)
			throws GeneralObfuscate4eException {
		if (qualifier == null) {
			throw new GeneralObfuscate4eException("qualifier must not be null!");
		}
		if (description == null) {
			description = "";
		}
		if (!checkForType(type)) {
			throw new GeneralObfuscate4eException("unknown type of Exclusion");
		}
		itsType = type;
		itsPath = qualifier;
		itsDescription = description;
	}

	private boolean checkForType(int type) {
		boolean correctType = false;
		for (int i = 0; i < ALL_TYPES.length; i++) {
			correctType = correctType || (ALL_TYPES[i] == type);
		}
		return correctType;
	}

	/**
	 * Indicates if exclusion is automatically generated from project settings.
	 * 
	 * @return <b>true</b> if exclusion is generated from project settings <br>
	 *         <b>false</b> if exclusion is due to user action
	 */
	public boolean isGenerated() {
		return itsFlagGenerated;
	}

	/**
	 * sets the generated flag
	 * 
	 * @param isGenerated
	 * <br>
	 *            <b>true</b> if exclusion is generated from project settings <br>
	 *            <b>false</b> if exclusion is due to user action
	 */
	public void setGenerated(boolean isGenerated) {
		this.itsFlagGenerated = isGenerated;
	}

	/**
	 * returns the fully qualified path
	 * 
	 * @return the fully qualified path
	 */
	public String getPath() {
		return itsPath;
	}

	/**
	 * returns the description, how this exclusion was instantiated
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return itsDescription;
	}

	/**
	 * return the exclusion type<br>
	 * values are CLASS, INTERFACE or PACKAGE
	 * 
	 * @return the exclusion type
	 */
	public int getType() {
		return itsType;
	}

	/**
	 * returns the relation type<br>
	 * values are EXTENDS, IMPLEMENTS or NO_RELATION
	 * 
	 * @return the relation type
	 */
	public int getRelation() {
		return itsRelation;
	}

	/**
	 * indicates if exclusion is of relating type. relating types are
	 * <b>extends</b> and <b>inherits</b>
	 * 
	 * @return <b>true</b> if exclusion is of relating type<br>
	 *         <b>false</b> if exclusion is non-relating
	 */
	public boolean isFlagRelating() {
		return itsFlagRelating;
	}

}

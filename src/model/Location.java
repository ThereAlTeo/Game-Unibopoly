package model;

import utilities.Pair;

/** 
 * Location<br>
 * Define the center point and the area of an entity.<br>
 * Center Point defined as x and y values in a cartesian plane.
 */
public class Location {
	
	private Pair<Double> position;
	private Area area;
	
	/**
	 * Constructor with one parameters.
	 * Create a new Location with the same fields as the input Location
	 * @param loc input location
	 */
	public Location(final Location loc) {
		this(loc.getX(), loc.getY(), new Area(loc.area));		
	}
		
	/**
	 * Constructor with params.
	 * Set location with input parameters
	 * @param initx abscissa value
	 * @param inity ordinate value
	 * @param initarea Area occupied by the entity
	 */
	public Location(final double initx, final double inity, final Area initarea) {		
		this.position = new Pair<>(initx, inity);
		this.area = initarea;
	}
	
	/**
	 * Getter of x coordinate of the Location.
	 * @return x abscissa value
	 */
	public double getX() {
		return this.position.getFirst();
	}
	
	/**
	 * Getter of y coordinate of the Location.
	 * @return y ordinate value
	 */
	public double getY() {
		return this.position.getSecond();
	}
	
	/**
	 * Getter of the Area of the Location.
	 * @return area as an Area
	 */
	public Area getArea() {
		return this.area;
	}
	
	/**
	 * Setter of x coordinate of the Location.
	 * @param newx abscissa value
	 */
	public void setX(final double newx) {
		this.position = new Pair<>(newx, this.position.getSecond());
	}
	
	/**
	 * Setter of y coordinate of the Location.
	 * @param newy ordinate value
	 */
	public void setY(final double newy) {
		this.position = new Pair<>(this.position.getFirst(), newy);
	}
	
	/**
	 * Setter of the Area of the Location.
	 * @param newarea as the new Area
	 */
	public void setArea(final Area newarea) {
		this.area = newarea;
	}
}
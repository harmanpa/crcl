//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:23:10 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 MoveScrewType is derived from MiddleCommandType.
 *                 An instance of MoveScrewType has the following elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 StartPosition (optional)
 *                 AxisPoint (optional)
 *                 AxialDistanceFree (optional)
 *                 AxialDistanceScrew
 *                 Turn.
 * 
 *                 This command is designed for attaching screws, nuts, and bolts.
 *                 It might also be used for drilling.
 * 
 *                 The command is executed as follows.
 * 
 *                 First, if the StartPosition exists, the controlled point and axis
 *                 are moved to the StartPosition along any convenient trajectory.
 * 
 *                 Second, if the AxialDistanceFree exists and is not zero, the
 *                 controlled point is moved along the axis by the given
 *                 AxialDistanceFree.
 * 
 *                 Third and finally, a screwing motion is made. If there is no
 *                 AxisPoint (or if an AxisPoint is given that is at the controlled
 *                 point), the gripper rotates around its axis through the angle given
 *                 by Turn at a constant rate while simultaneously translating along
 *                 the axis at a constant rate (the currently set speed) through the
 *                 AxialDistanceScrew so that the rotation and translation finish at
 *                 the same time. If there is an AxisPoint and it differs from the
 *                 location of the controlled point, the controlled point
 *                 simultaneously (1) rotates as above, (2) revolves around an axis
 *                 through the AxisPoint parallel to the controlled axis, and (3)
 *                 translates as above. That makes a helical motion of the controlled
 *                 point. The motion along the helix is done at the currently set
 *                 speed.
 * 
 *                 A positive value of AxialDistanceFree or AxialDistanceScrew means
 *                 to move away from the end effector. A negative value means to move
 *                 toward the end effector.
 * 
 *                 A positive value of Turn means to rotate (and possibly revolve) in
 *                 a counterclockwise sense as viewed from the positive Z axis of the
 *                 gripper (the region extending away from the gripper).
 * 
 *                 The robot must reach the EndPosition within the tolerance
 *                 established (1) by the tolerance given for the pose in the
 *                 EndPosition, if there is a tolerance in the EndPosition, or if not
 *                 (2) by the most recently executed instance of
 *                 SetEndPoseToleranceType. The speed and acceleration to use are set
 *                 either in the EndPosition or by previously executed CRCL commands.
 * 
 *                 In an instance file, the type of StartPosition may be either
 *                 PoseType or PoseAndSetType, which is derived from PoseType.
 *             
 * 
 * <p>Java class for MoveScrewType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MoveScrewType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StartPosition" type="{}PoseType" minOccurs="0"/&gt;
 *         &lt;element name="AxisPoint" type="{}PointType" minOccurs="0"/&gt;
 *         &lt;element name="AxialDistanceFree" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="AxialDistanceScrew" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Turn" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MoveScrewType", propOrder = {
    "startPosition",
    "axisPoint",
    "axialDistanceFree",
    "axialDistanceScrew",
    "turn"
})
public class MoveScrewType
    extends MiddleCommandType
{

    @XmlElement(name = "StartPosition")
    protected PoseType startPosition;
    @XmlElement(name = "AxisPoint")
    protected PointType axisPoint;
    @XmlElement(name = "AxialDistanceFree")
    protected Double axialDistanceFree;
    @XmlElement(name = "AxialDistanceScrew")
    protected double axialDistanceScrew;
    @XmlElement(name = "Turn")
    protected double turn;

    /**
     * Gets the value of the startPosition property.
     * 
     * @return
     *     possible object is
     *     {@link PoseType }
     *     
     */
    public PoseType getStartPosition() {
        return startPosition;
    }

    /**
     * Sets the value of the startPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoseType }
     *     
     */
    public void setStartPosition(PoseType value) {
        this.startPosition = value;
    }

    /**
     * Gets the value of the axisPoint property.
     * 
     * @return
     *     possible object is
     *     {@link PointType }
     *     
     */
    public PointType getAxisPoint() {
        return axisPoint;
    }

    /**
     * Sets the value of the axisPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PointType }
     *     
     */
    public void setAxisPoint(PointType value) {
        this.axisPoint = value;
    }

    /**
     * Gets the value of the axialDistanceFree property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAxialDistanceFree() {
        return axialDistanceFree;
    }

    /**
     * Sets the value of the axialDistanceFree property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAxialDistanceFree(Double value) {
        this.axialDistanceFree = value;
    }

    /**
     * Gets the value of the axialDistanceScrew property.
     * 
     */
    public double getAxialDistanceScrew() {
        return axialDistanceScrew;
    }

    /**
     * Sets the value of the axialDistanceScrew property.
     * 
     */
    public void setAxialDistanceScrew(double value) {
        this.axialDistanceScrew = value;
    }

    /**
     * Gets the value of the turn property.
     * 
     */
    public double getTurn() {
        return turn;
    }

    /**
     * Sets the value of the turn property.
     * 
     */
    public void setTurn(double value) {
        this.turn = value;
    }

}

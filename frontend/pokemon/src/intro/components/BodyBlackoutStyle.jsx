import React from "react";
import "./BodyBlackoutStyle.css";

export default function BodyBlackoutStyle({ onSetIsLoginModalVisible }) {

  return (
    <div 
      className="body-blackout-style" 
      onClick={() => onSetIsLoginModalVisible(false) }
    />
  );
}

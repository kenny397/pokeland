import React from "react";
import "./BodyBlackoutStyle.css";

export default function BodyBlackoutStyle({ setIsModalVisible }) {

  return (
    <div 
      className="body-blackout-style" 
      onClick={() => setIsModalVisible(false) }
    />
  );
}

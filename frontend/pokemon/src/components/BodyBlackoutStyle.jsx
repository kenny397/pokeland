import React from "react";
import "./BodyBlackoutStyle.scss";

export default function BodyBlackoutStyle({ setIsModalVisible }) {

  return (
    <div 
      className="body-blackout-style" 
      onClick={() => setIsModalVisible(false) }
    />
  );
}

import React from "react";
import "./Modal.css";

export default function Modal({ setIsVisible, InnerComponent }) {

  return (
    <div className="modal-container">
      <InnerComponent handleClickCloseModal={() => setIsVisible(false)}/>
    </div>
  );
}

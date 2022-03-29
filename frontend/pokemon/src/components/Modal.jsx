import React from "react";
import "./Modal.scss";

export default function Modal({ setIsVisible, InnerComponent }) {

  return (
    <div className="modal-container">
      <p className="modal-close-handler" onClick={() => setIsVisible(false)}>X</p>
      <InnerComponent handleClickCloseModal={() => setIsVisible(false)}/>
    </div>
  );
}

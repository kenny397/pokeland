import React from "react";
import "./Modal.scss";

export default function Modal({ setIsVisible, InnerComponent, width, height }) {

  return (
    <div className="modal-container" style={ { width: width+'', height: height+'' } }>
      <p className="modal-close-handler" onClick={() => setIsVisible(false)}>X</p>
      <InnerComponent handleClickCloseModal={() => setIsVisible(false)}/>
    </div>
  );
}

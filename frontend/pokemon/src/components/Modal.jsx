import React from "react";
import "./Modal.scss";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from "@fortawesome/free-solid-svg-icons";

export default function Modal({ setIsVisible, InnerComponent, width, height }) {

  return (
    <div className="modal-container" style={ { width: width+'', height: height+'' } }>
      <FontAwesomeIcon icon={faXmark} className="modal-close-handler" onClick={() => setIsVisible(false)}/>
      <InnerComponent handleClickCloseModal={() => setIsVisible(false)}/>
    </div>
  );
}

import React from "react";
import "./LoginModal.css";

export default function LoginModal({ setIsLoginModalVisible }) {

  const onSubmitBtn = () => {
    setIsLoginModalVisible(false);
  };

  return (
    <div className="modal-container">
      <button onClick={() => onSubmitBtn()}>로그인</button>
    </div>
  );
}

import React from "react";
import { useRoutes } from "react-router-dom";

import IntroPage from './IntroPage';

export const Router = () => {
  let element = useRoutes([
    { path: "/", element: <IntroPage /> },
  ]);

  return element;
};

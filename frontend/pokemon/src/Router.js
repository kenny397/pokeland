import React from "react";
import { useRoutes } from "react-router-dom";

import IntroPage from './IntroPage';
import SupportPage from './SupportPage';

const Router = () => {
  let element = useRoutes([
    { path: "/", element: <IntroPage /> },
    { path: "/support", element: <SupportPage /> },
  ]);

  return element;
};

export default Router;

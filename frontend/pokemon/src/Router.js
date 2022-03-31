import React from "react";
import { Navigate, useRoutes } from "react-router-dom";

import IntroPage from './intro/IntroPage';
import MainPage from './mainpage/MainPage';
import TutorialPage from './tutorial/TutorialPage';
import SupportPage from './support/SupportPage';
import PokedexPage from './pokedex/PokedexPage';
import PokemonNotFound from "./pokedex/PokemonNotFound";
import NfpsByPokemon from './pokedex/NfpsByPokemon';
import NotFound from './pages/NotFound';
import GachaContainer from "./gacha/GachaContainer";
import SignupPage from "./signup/SignupPage";

export const routers = [
  { path: "/", element: <IntroPage /> },
  { path: "/main", element: <MainPage /> },
  { path: "/tutorial", element: <TutorialPage /> },
  { path: "/support", element: <SupportPage /> },
  { path: "/pokedex", element: <Navigate replace to="/pokedex/1"/> },
  { path: "/pokedex/:page", element: <PokedexPage /> },
  { path: "/pokedex/pokemon-not-found/:pokedexId", element: <PokemonNotFound /> },
  { path: "/pokedex/nfps/:pokedexId", element: <NfpsByPokemon /> },
  { path: "/gacha", element: <GachaContainer/> },
  { path: "/signup", element: <SignupPage/> },
  { path: "/swagger-ui" },
  { path: "*", element: <NotFound /> },
];

export const Router = () => {
  let element = useRoutes(routers);
  return element;
};

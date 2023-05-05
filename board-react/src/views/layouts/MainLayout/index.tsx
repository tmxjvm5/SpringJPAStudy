import React, { useEffect, useState } from 'react'
import Navigation from '../../Navigation'
import Authentication from '../../Authentication'
import BoardMain from '../../BoardMain'
import { useUserStore } from '../../../stores'
import { useCookies } from 'react-cookie'
import axios from 'axios'

export default function MainLayout() {
  const[cookies] = useCookies();
  const{ user } = useUserStore();
  const[boardResponse, setBoardResponse] = useState<string>('');
  
  const getBoard = async (token: string) => {
    await axios.get('http://localhost:4000/api/board/',{
      headers :{
        Authorization: `Bearer ${token}`
      }
    }).then((response) => {
      setBoardResponse(response.data);

    }).catch((error) => '');
  
  }

  useEffect(() =>{
    const token = cookies.token;
    if(token) getBoard(token); 
    } , [user]);
  
  
  return (
    <>
    <Navigation />
    {boardResponse ? (<BoardMain />) : (<Authentication />)}
    
    </>
  )
}

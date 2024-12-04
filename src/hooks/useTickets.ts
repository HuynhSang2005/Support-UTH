import { useState, useEffect } from 'react';
import { Ticket } from '../types/tickets/ticket';
import { mockTickets } from '../data/mockTickets';

export const useTickets = () => {
  const [tickets, setTickets] = useState<Ticket[]>([]);

  const fetchTickets = async () => {
    // Giả lập API call
    await new Promise(resolve => setTimeout(resolve, 1000));
    setTickets(mockTickets);
  };

  const addTicket = (newTicket: Ticket) => {
    setTickets(prev => [newTicket, ...prev]);
  };

  useEffect(() => {
    fetchTickets();
  }, []);

  return {
    tickets,
    addTicket,
    fetchTickets
  };
};
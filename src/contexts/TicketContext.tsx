import { createContext, useContext } from 'react';
import { Ticket } from '../types/tickets/ticket'; 
import { useTickets } from '../hooks/useTickets';

const TicketContext = createContext<ReturnType<typeof useTickets> | undefined>(undefined);

export function TicketProvider({ children }: { children: React.ReactNode }) {
  const { tickets, addTicket, fetchTickets } = useTickets(); // Sử dụng useTickets

  const value = {
    tickets,
    addTicket,
    fetchTickets
  };

  return (
    <TicketContext.Provider value={value}>
      {children}
    </TicketContext.Provider>
  );
}

export const useTicketContext = () => {
  const context = useContext(TicketContext);
  if (!context) throw new Error('useTicketContext must be used within TicketProvider');
  return context;
};
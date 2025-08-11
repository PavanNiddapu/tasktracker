import { render, screen } from '@testing-library/react';
import App from './App';

test('renders hello from react text', () => {
  render(<App />);
  const textElements = screen.getAllByText(/hello from react/i);
  expect(textElements).toHaveLength(2);
  expect(textElements[0]).toBeInTheDocument();
});

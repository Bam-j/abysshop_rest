import { create } from 'zustand';

const useUserStore = create(set => ({
  user: null,
  cartQuantity: 0,

  setUser: user => set({ user }),
  setCartQuantity: quantity => set({ cartQuantity: quantity }),
  resetUser: () => set({ user: null, cartQuantity: 0 }),
}));

export default useUserStore;

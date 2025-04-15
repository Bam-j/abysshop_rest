import { create } from 'zustand';
import axios from 'axios';

const initialState = {
  user: null,
  cartQuantity: 0,
};

const useUserStore = create((set, get) => ({
  ...initialState,

  setUser: user => set({ user }),
  setCartQuantity: quantity => set({ cartQuantity: quantity }),
  resetUser: () => set(initialState),

  updateCartQuantity: async userParam => {
    const token = localStorage.getItem('accessToken');
    const user = userParam || get().user;

    if (!token || !user) {
      return;
    }

    try {
      const res = await axios.get(
        `http://localhost:8080/api/carts/${user.cartId}/quantity`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );
      const quantity = typeof res.data === 'number' ? res.data : res.data.quantity;
      set({ cartQuantity: quantity });
    } catch (error) {
      console.error('장바구니 수량 업데이트 실패:', error);
    }
  },
}));

export default useUserStore;

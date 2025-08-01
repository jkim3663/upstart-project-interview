import React, { useEffect, useState }from 'react';
import { DialogPanel, DialogTitle, Description, Dialog } from '@headlessui/react';
import './Dashboard.css';
import { PRODUCT_API_URL } from '../../constants/constants';

const Dashboard = () => {
  const [products, setProducts] = useState([]);
  const [isSubmitOpen, setSubmitOpen] = useState(false);
  const [isDeleteOpen, setDeleteOpen] = useState(false);
  const [productName, setProductName] = useState('');
  const [productPrice, setProductPrice] = useState('');
  const [productIdToDelete, setProductIdToDelete] = useState(null);

  const fetchAllProducts = async ()=> {
    try {
      const resp = await fetch(PRODUCT_API_URL);
      if (!resp.status === 200) {
        throw new Error('Failed to fetch products');
      }
      const data = await resp.json();
      setProducts(data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  const handleAddProduct = async () => {
    try {
      const resp = await fetch(PRODUCT_API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify({ name: productName, price :productPrice})
      });
      setSubmitOpen(false);
      setProductName('');
      setProductPrice('');
      fetchAllProducts(); // Refresh list
    } catch (error) {
      console.error('Error adding product:', error);
    }
  };

  const handleDeleteProduct = async (id) => {
    try {
      const resp = await fetch(`${PRODUCT_API_URL}/${id}`, {
        method: 'DELETE',
      });
      setDeleteOpen(false);
      fetchAllProducts();
    } catch (error) {
      console.error('Error deleting product: ', error);
    }
  };

  useEffect(() => {
    fetchAllProducts();
  }, []);

  return (
    <div className='container'>
      <div className='header'>
        <div className='header-text'>View Your Favorite Products</div>
      </div>
      <div className='body'>
        <div className='product-table'>
          <table>
            <thead>
              <tr>
                <th>Product Name</th>
                <th>Product Price</th>
                <th>Created At</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
                {products.map((product, idx) => (
                  <tr key={product.id}>
                    <td>{product.name}</td>
                    <td>{product.price}</td>
                    <td>{product.createdAt}</td>
                    <td>
                      <button onClick={() => {
                        setProductIdToDelete(product.id);
                        setDeleteOpen(true);
                      }}>
                        Delete
                        </button>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
        <div className='add-new-product'>
          <div className='header'>
            <div className='header-text'>
              Add New Products
            </div>
          </div>
          <div className='product-form'>
            <label>Enter product name:</label>
            <input type='text' value={productName} onChange={e => setProductName(e.target.value)}></input>
            <label>Enter the price of product (in format xxx.yy):</label>
            <input type='text' value={productPrice} onChange={e => setProductPrice(e.target.value)}></input>
            <button onClick={() => setSubmitOpen(true)}>
              Add
            </button>
          </div>
        </div>
      </div>
      <Dialog open={isSubmitOpen} onClose={() => setSubmitOpen(false)} className="relative z-50">
            <div className="fixed inset-0 flex w-screen items-center justify-center p-4">
              <DialogPanel className="max-w-lg space-y-4 border bg-white p-12">
                <DialogTitle className="font-bold">Submit product</DialogTitle>
                <Description>This will submit a new product to system</Description>
                <p>Are you sure you want to submit a new product?</p>
                <div className="flex gap-4">
                  <button onClick={() => handleAddProduct()}>Submit</button>
                  <button onClick={() => setSubmitOpen(false)}>Cancel</button>
                </div>
              </DialogPanel>
            </div>
      </Dialog>
      <Dialog open={isDeleteOpen} onClose={() => setDeleteOpen(false)} className="relative z-50">
            <div className="fixed inset-0 flex w-screen items-center justify-center p-4">
              <DialogPanel className="max-w-lg space-y-4 border bg-white p-12">
                <DialogTitle className="font-bold">Delete product</DialogTitle>
                <Description>This will delete the product from system</Description>
                <p>Are you sure you want to delete the product?</p>
                <div className="flex gap-4">
                  <button onClick={() => handleDeleteProduct(productIdToDelete)}>Delete</button>
                  <button onClick={() => setDeleteOpen(false)}>Cancel</button>
                </div>
              </DialogPanel>
            </div>
      </Dialog>
    </div>
  );
};

export default Dashboard;
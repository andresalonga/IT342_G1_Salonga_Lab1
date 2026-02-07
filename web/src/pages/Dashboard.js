import React from 'react';
import { useAuth } from '../context/AuthContext';
import './Dashboard.css';

const Dashboard = () => {
  const { user, logout } = useAuth();

  const handleLogout = () => {
    logout();
  };

  return (
    <div className="dashboard-container">
      <nav className="dashboard-nav">
        <div className="nav-brand">
          <h1>User Dashboard</h1>
        </div>
        <div className="nav-user">
          <span className="user-email">{user?.email || 'User'}</span>
          <button onClick={handleLogout} className="logout-button">
            Logout
          </button>
        </div>
      </nav>
      
      <main className="dashboard-content">
        <div className="profile-card">
          <div className="profile-header">
            <div className="avatar">
              {user?.email?.charAt(0).toUpperCase() || 'U'}
            </div>
            <div className="user-info">
              <h2>Welcome, {user?.email?.split('@')[0] || 'User'}!</h2>
              <p className="user-email-display">{user?.email}</p>
              {user?.userId && <p className="user-id">User ID: {user.userId}</p>}
            </div>
          </div>
          
          <div className="profile-details">
            <h3>Account Information</h3>
            <div className="info-row">
              <span className="label">Email:</span>
              <span className="value">{user?.email}</span>
            </div>
            <div className="info-row">
              <span className="label">User ID:</span>
              <span className="value">{user?.userId || 'N/A'}</span>
            </div>
            <div className="info-row">
              <span className="label">Status:</span>
              <span className="value status-active">Active</span>
            </div>
          </div>
          
          <div className="profile-actions">
            <button onClick={handleLogout} className="action-button logout">
              Logout
            </button>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Dashboard;

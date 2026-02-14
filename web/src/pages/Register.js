import React, { useState, useMemo } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { register } from '../services/api';
import { Mail, Lock, Eye, EyeOff, Loader2, CheckCircle, User, AlertCircle, Check } from 'lucide-react';

const Register = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);
  
  const navigate = useNavigate();

  // Password strength calculation
  const passwordStrength = useMemo(() => {
    if (!password) return { score: 0, label: '', color: '' };
    
    let score = 0;
    const checks = {
      length: password.length >= 8,
      lowercase: /[a-z]/.test(password),
      uppercase: /[A-Z]/.test(password),
      number: /[0-9]/.test(password),
      special: /[!@#$%^&*(),.?":{}|<>]/.test(password),
    };
    
    if (checks.length) score += 1;
    if (checks.lowercase) score += 1;
    if (checks.uppercase) score += 1;
    if (checks.number) score += 1;
    if (checks.special) score += 1;
    
    const labels = ['Very Weak', 'Weak', 'Fair', 'Good', 'Strong'];
    const colors = ['bg-red-500', 'bg-orange-500', 'bg-yellow-500', 'bg-blue-500', 'bg-green-500'];
    
    return { 
      score, 
      label: labels[Math.min(score - 1, 4)] || '',
      color: colors[Math.min(score - 1, 4)] || '',
      checks
    };
  }, [password]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess(false);
    setLoading(true);

    // Validate passwords match
    if (password !== confirmPassword) {
      setError('Passwords do not match');
      setLoading(false);
      return;
    }

    // Validate password strength
    if (password.length < 8) {
      setError('Password must be at least 8 characters');
      setLoading(false);
      return;
    }

    try {
      await register(email, password, firstName, lastName);
      setSuccess(true);
      
      // Auto redirect to login after successful registration
      setTimeout(() => {
        navigate('/login');
      }, 1500);
    } catch (err) {
      setError(err.message || err.error || 'Registration failed. Email may already be in use.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 via-gray-50 to-slate-100 flex items-center justify-center p-4">
      {/* Background decoration */}
      <div className="absolute inset-0 overflow-hidden">
        <div className="absolute -top-40 -right-40 w-80 h-80 bg-teal-200 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-pulse"></div>
        <div className="absolute -bottom-40 -left-40 w-80 h-80 bg-primary-200 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-pulse" style={{ animationDelay: '1s' }}></div>
      </div>

      <div className="relative w-full max-w-md">
        {/* Logo / Brand */}
        <div className="text-center mb-8">
          <div className="inline-flex items-center justify-center w-16 h-16 bg-gradient-to-br from-teal-500 to-teal-700 rounded-2xl shadow-lg shadow-teal-500/30 mb-4">
            <User className="w-8 h-8 text-white" />
          </div>
          <h1 className="text-2xl font-bold text-gray-900">Create Account</h1>
          <p className="text-gray-500 mt-2">Join us and get started today</p>
        </div>

        <div className="bg-white/80 backdrop-blur-xl rounded-3xl shadow-xl shadow-gray-200/50 border border-white/50 p-8">
          {/* Error Alert */}
          {error && (
            <div className="mb-6 p-4 bg-red-50 border border-red-100 rounded-xl animate-fade-in">
              <div className="flex items-center gap-3">
                <AlertCircle className="w-5 h-5 text-red-600 flex-shrink-0" />
                <p className="text-sm text-red-700 font-medium">{error}</p>
              </div>
            </div>
          )}

          {/* Success Alert */}
          {success && (
            <div className="mb-6 p-4 bg-green-50 border border-green-100 rounded-xl animate-fade-in">
              <div className="flex items-center gap-3">
                <CheckCircle className="w-5 h-5 text-green-600" />
                <p className="text-sm text-green-700 font-medium">Account created! Redirecting to login...</p>
              </div>
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-4">
            {/* Name Fields */}
            <div className="grid grid-cols-2 gap-4">
              {/* First Name */}
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
                  <User className="h-5 w-5 text-gray-400" />
                </div>
                <input
                  type="text"
                  id="firstName"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  className="w-full pl-12 pr-4 py-3.5 bg-gray-50 border-2 border-gray-100 rounded-xl text-gray-900 placeholder-transparent focus:outline-none focus:border-primary-500 focus:bg-white transition-all duration-200"
                  placeholder="First Name"
                  disabled={loading || success}
                />
                <label 
                  htmlFor="firstName" 
                  className="absolute left-12 -top-2.5 px-1 text-sm text-gray-500 bg-white rounded-md transition-all duration-200 pointer-events-none"
                >
                  First Name
                </label>
              </div>

              {/* Last Name */}
              <div className="relative">
                <input
                  type="text"
                  id="lastName"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  className="w-full pl-4 pr-4 py-3.5 bg-gray-50 border-2 border-gray-100 rounded-xl text-gray-900 placeholder-transparent focus:outline-none focus:border-primary-500 focus:bg-white transition-all duration-200"
                  placeholder="Last Name"
                  disabled={loading || success}
                />
                <label 
                  htmlFor="lastName" 
                  className="absolute left-4 -top-2.5 px-1 text-sm text-gray-500 bg-white rounded-md transition-all duration-200 pointer-events-none"
                >
                  Last Name
                </label>
              </div>
            </div>
            
            {/* Email Field */}
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
                <Mail className="h-5 w-5 text-gray-400" />
              </div>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full pl-12 pr-4 py-3.5 bg-gray-50 border-2 border-gray-100 rounded-xl text-gray-900 placeholder-transparent focus:outline-none focus:border-primary-500 focus:bg-white transition-all duration-200"
                placeholder="Email Address"
                required
                disabled={loading || success}
              />
              <label 
                htmlFor="email" 
                className="absolute left-12 -top-2.5 px-1 text-sm text-gray-500 bg-white rounded-md transition-all duration-200 pointer-events-none"
              >
                Email Address
              </label>
            </div>
            
            {/* Password Field with Show/Hide */}
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
                <Lock className="h-5 w-5 text-gray-400" />
              </div>
              <input
                type={showPassword ? "text" : "password"}
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full pl-12 pr-14 py-3.5 bg-gray-50 border-2 border-gray-100 rounded-xl text-gray-900 placeholder-transparent focus:outline-none focus:border-primary-500 focus:bg-white transition-all duration-200"
                placeholder="Password"
                required
                disabled={loading || success}
              />
              <label 
                htmlFor="password" 
                className="absolute left-12 -top-2.5 px-1 text-sm text-gray-500 bg-white rounded-md transition-all duration-200 pointer-events-none"
              >
                Password
              </label>
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute inset-y-0 right-0 pr-4 flex items-center text-gray-400 hover:text-gray-600 transition-colors"
                disabled={loading || success}
              >
                {showPassword ? (
                  <EyeOff className="h-5 w-5" />
                ) : (
                  <Eye className="h-5 w-5" />
                )}
              </button>
            </div>

            {/* Password Strength Meter */}
            {password && (
              <div className="space-y-2">
                <div className="flex gap-1">
                  {[1, 2, 3, 4, 5].map((level) => (
                    <div
                      key={level}
                      className={`h-1.5 flex-1 rounded-full transition-all duration-300 ${
                        level <= passwordStrength.score ? passwordStrength.color : 'bg-gray-200'
                      }`}
                    />
                  ))}
                </div>
                <div className="flex justify-between items-center text-xs">
                  <span className="text-gray-500">
                    {passwordStrength.label && `Strength: ${passwordStrength.label}`}
                  </span>
                </div>
                
                {/* Password Requirements */}
                <div className="grid grid-cols-2 gap-1 mt-2">
                  <div className={`text-xs flex items-center gap-1 ${passwordStrength.checks.length ? 'text-green-600' : 'text-gray-400'}`}>
                    <Check className={`w-3 h-3 ${passwordStrength.checks.length ? 'opacity-100' : 'opacity-30'}`} />
                    8+ characters
                  </div>
                  <div className={`text-xs flex items-center gap-1 ${passwordStrength.checks.lowercase ? 'text-green-600' : 'text-gray-400'}`}>
                    <Check className={`w-3 h-3 ${passwordStrength.checks.lowercase ? 'opacity-100' : 'opacity-30'}`} />
                    Lowercase
                  </div>
                  <div className={`text-xs flex items-center gap-1 ${passwordStrength.checks.uppercase ? 'text-green-600' : 'text-gray-400'}`}>
                    <Check className={`w-3 h-3 ${passwordStrength.checks.uppercase ? 'opacity-100' : 'opacity-30'}`} />
                    Uppercase
                  </div>
                  <div className={`text-xs flex items-center gap-1 ${passwordStrength.checks.number ? 'text-green-600' : 'text-gray-400'}`}>
                    <Check className={`w-3 h-3 ${passwordStrength.checks.number ? 'opacity-100' : 'opacity-30'}`} />
                    Number
                  </div>
                </div>
              </div>
            )}
            
            {/* Confirm Password Field */}
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
                <Lock className="h-5 w-5 text-gray-400" />
              </div>
              <input
                type={showConfirmPassword ? "text" : "password"}
                id="confirmPassword"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                className="w-full pl-12 pr-14 py-3.5 bg-gray-50 border-2 border-gray-100 rounded-xl text-gray-900 placeholder-transparent focus:outline-none focus:border-primary-500 focus:bg-white transition-all duration-200"
                placeholder="Confirm Password"
                required
                disabled={loading || success}
              />
              <label 
                htmlFor="confirmPassword" 
                className="absolute left-12 -top-2.5 px-1 text-sm text-gray-500 bg-white rounded-md transition-all duration-200 pointer-events-none"
              >
                Confirm Password
              </label>
              <button
                type="button"
                onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                className="absolute inset-y-0 right-0 pr-4 flex items-center text-gray-400 hover:text-gray-600 transition-colors"
                disabled={loading || success}
              >
                {showConfirmPassword ? (
                  <EyeOff className="h-5 w-5" />
                ) : (
                  <Eye className="h-5 w-5" />
                )}
              </button>
            </div>

            {/* Password Match Indicator */}
            {confirmPassword && (
              <div className={`text-xs flex items-center gap-1 ${password === confirmPassword ? 'text-green-600' : 'text-red-500'}`}>
                {password === confirmPassword ? (
                  <Check className="w-3 h-3" />
                ) : (
                  <AlertCircle className="w-3 h-3" />
                )}
                {password === confirmPassword ? 'Passwords match' : 'Passwords do not match'}
              </div>
            )}
            
            {/* Submit Button with Loading State */}
            <button 
              type="submit" 
              className="w-full py-3.5 px-6 bg-gradient-to-r from-teal-500 to-teal-600 hover:from-teal-600 hover:to-teal-700 text-white font-semibold rounded-xl shadow-lg shadow-teal-500/30 hover:shadow-teal-500/40 transition-all duration-200 transform hover:-translate-y-0.5 disabled:opacity-70 disabled:cursor-not-allowed disabled:transform-none flex items-center justify-center gap-2 mt-6"
              disabled={loading || success}
            >
              {loading ? (
                <>
                  <Loader2 className="w-5 h-5 animate-spin" />
                  Creating Account...
                </>
              ) : success ? (
                <>
                  <CheckCircle className="w-5 h-5" />
                  Account Created!
                </>
              ) : (
                'Create Account'
              )}
            </button>
          </form>
          
          {/* Footer Link */}
          <div className="mt-8 text-center">
            <p className="text-gray-600">
              Already have an account?{' '}
              <Link to="/login" className="text-primary-600 hover:text-primary-700 font-semibold transition-colors">
                Sign in
              </Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;

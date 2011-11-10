require File.join(File.dirname(__FILE__), '..', 'lib', 'tic_tac_toe')

require 'rubygems'
require 'rack/test'
require 'rspec'
require 'webrat'

Webrat.configure do |config|
 config.mode = :rack
end

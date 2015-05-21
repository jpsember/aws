#!/usr/bin/env ruby

require 'js_base'

scall("brew install tomcat")
USER = ENV['USER']
HOME_DIR = File.expand_path('~')
TOMCAT_DIR = File.join(HOME_DIR,"tomcat")
scall("sudo ln -s /usr/local/Cellar/tomcat/8.0.22/libexec #{TOMCAT_DIR}")
scall("sudo chown -R #{USER} #{TOMCAT_DIR}")

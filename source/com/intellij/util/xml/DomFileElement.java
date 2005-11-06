/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.intellij.util.xml;

import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * @author peter
 */
public interface DomFileElement<T extends DomElement> extends DomElement {
  XmlFile getFile();

  @Nullable
  XmlTag getRootTag();

  @NotNull
  T getRootElement();
}

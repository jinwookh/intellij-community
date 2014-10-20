/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.openapi.wm.impl.welcomeScreen;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.vcs.CheckoutProvider;
import com.intellij.openapi.vcs.checkout.CheckoutAction;
import com.intellij.ui.UIBundle;

import java.util.Arrays;

public class GetFromVcsAction extends WelcomePopupAction{

  protected void fillActions(DefaultActionGroup group) {
    final CheckoutProvider[] providers = Extensions.getExtensions(CheckoutProvider.EXTENSION_POINT_NAME);
    Arrays.sort(providers, new CheckoutProvider.CheckoutProviderComparator());
    for (CheckoutProvider provider : providers) {
      group.add(new CheckoutAction(provider));
    }
  }

  protected String getCaption() {
    return UIBundle.message("welcome.screen.get.from.vcs.action.checkout.from.list.popup.title");
  }

  protected String getTextForEmpty() {
    return UIBundle.message("welcome.screen.get.from.vcs.action.no.vcs.plugins.with.check.out.action.installed.action.name");
  }

  @Override
  protected boolean isSilentlyChooseSingleOption() {
    return true;
  }

  @Override
  public void update(AnActionEvent e) {
    e.getPresentation().setEnabled(Extensions.getExtensions(CheckoutProvider.EXTENSION_POINT_NAME).length > 0);
    if (e.getPlace() == ActionPlaces.WELCOME_SCREEN && Registry.is("ide.new.welcome.screen")) {
      e.getPresentation().setIcon(AllIcons.Welcome.FromVCS);
    }
  }
}
